package com.demo.crypto;

import java.io.IOException;
import java.time.Instant;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RestServiceToFeed {
	private final OkHttpClient client = new OkHttpClient();
	
	
	public static void main(String[] args) throws IOException {
		RestServiceToFeed restObj=new RestServiceToFeed();
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the API Id");
		String key=sc.next();
		
		Quote_with_trade[] result = quotes_get_for_all_symbols(key);
		for (Quote_with_trade t : result) {
			System.out.println(quote_with_trade_to_string(t));
		}
		sc.close();

	}
	
	private static String quote_with_trade_to_string(Quote_with_trade q) {
		if (q.has_last_trade()) {
			return String.format("Quote_with_trade (symbol = %s, exchange time = %s, CoinAPI time: %s)\n    ask (Price = %.2f, Size = %.6f)\n    bid (Price = %.2f, Size = %.6f)\n    Last %s",
				q.get_symbol_id(), q.get_time_exchange(), q.get_time_coinapi(), q.get_ask_price(), q.get_ask_size(), q.get_bid_price(), q.get_bid_size(),
				trade_to_string(q.get_last_trade()));
		} else {
			return String.format("Quote_with_trade (symbol = %s, exchange time = %s, CoinAPI time: %s)\n    ask (Price = %.2f, Size = %.6f)\n    bid (Price = %.2f, Size = %.6f)",
				q.get_symbol_id(), q.get_time_exchange(), q.get_time_coinapi(), q.get_ask_price(), q.get_ask_size(), q.get_bid_price(), q.get_bid_size());
		}
	}
	
	private static String trade_to_string(Trade t) {
		return String.format("Trade (symbol = %s, exchange time = %s, CoinAPI time: %s)\n    UUID = %s, price = %.2f, size = %.6f, taker side: %s",
			t.get_symbol_id(), t.get_time_exchange(), t.get_time_coinapi(), t.get_uuid(), t.get_price(), t.get_size(), t.get_taker_side().toString());
	}
	
	public static Quote_with_trade[] quotes_get_for_all_symbols(String key) throws IOException {
		RestServiceToFeed restObj=new RestServiceToFeed();
		String json = restObj.get_json("/v1/quotes/current",key);
		JSONArray array = new JSONArray(json);
		return restObj.parse_quotes_with_trade(array);
	}
	private Quote_with_trade[] parse_quotes_with_trade(JSONArray array) {
		Quote_with_trade[] result = new Quote_with_trade[array.length()];
		for (int i = 0; i < array.length(); i++) {
			result[i] = parse_quote_with_trade(array.getJSONObject(i));
		}
		return result;
	}
	
	private String get_json(String url,String key) throws IOException {
		//RequestBody body = RequestBody.create(null, new byte[0]);
		
		Request request = new Request.Builder()
			.url("https://rest.coinapi.io" + url)
			//.post(body)
			.addHeader("X-CoinAPI-Key", key)
			.build();
		
		try (
			Response response = client.newCall(request).execute()    // you MUST always immediately close response, easiest way is like this; see https://square.github.io/okhttp/3.x/okhttp/okhttp3/ResponseBody.html 
		) {
			ResponseBody body = response.body();    // no need to null check the result of body(): it is annotated @Nullable but its javadoc states that "Returns a non-null value if this response was ... returned from Call.execute()" which is the case above; see https://square.github.io/okhttp/3.x/okhttp/okhttp3/Response.html#body--
			
			if (response.code() >= 400) {
				String error;
				try {
					JSONObject object = new JSONObject(body.string());
					if (object.has("error")) {
						error = object.getString("error");
					} else {
						error = "[NOTHING: response has no value for \"error\"]";
					}
				} catch (Throwable t) {
					error = "[FAILED to extract response's \"error\" value because this Throwable was raised: " + t + "]";
				}
				
				String message =
					"the response code for url is an ERROR code:" + "\n"
					+ "\t" + "url = " + url + "\n"
					+ "\t" + "response code = " + response.code() + "\n"
					+ "\t" + "response body error = " + error + "\n";
				
				throw new RuntimeException(message);
			}
			
			return body.string();
		}
	}
	
	private  Quote_with_trade parse_quote_with_trade(JSONObject object) {
		String symbol_id = object.getString("symbol_id");
		Instant time_exchange = Instant.parse(object.getString("time_exchange"));
		Instant time_coinapi = Instant.parse(object.getString("time_coinapi"));
		double ask_price = object.getDouble("ask_price");
		double ask_size = object.getDouble("ask_size");
		double bid_price = object.getDouble("bid_price");
		double bid_size = object.getDouble("bid_size");
		Trade last_trade = null;
		if (object.has("last_trade")) last_trade = parse_trade(object.getJSONObject("last_trade"), symbol_id);
		return new Quote_with_trade(symbol_id, time_exchange, time_coinapi, ask_price, ask_size, bid_price, bid_size, last_trade);
	}
	
	private Trade parse_trade(JSONObject object, String symbol_id) {
		if (symbol_id == null) symbol_id = object.getString("symbol_id");
		Instant time_exchange = Instant.parse(object.getString("time_exchange"));
		Instant time_coinapi = Instant.parse(object.getString("time_coinapi"));
		String uuid = object.getString("uuid");
		double price = object.getDouble("price");
		double size = object.getDouble("size");
		Taker_side taker_side = Taker_side.valueOf(object.getString("taker_side"));
		
		return new Trade(symbol_id, time_exchange, time_coinapi, uuid, price, size, taker_side);
	}
	
	

}
