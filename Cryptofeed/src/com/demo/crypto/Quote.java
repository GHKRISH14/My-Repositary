package com.demo.crypto;


import java.time.Instant;


public class Quote {

	
	private final String symbol_id;

	
	private final Instant time_exchange;

	
	private final Instant time_coinapi;

	
	private final double ask_price;

		private final double ask_size;

	
	private final double bid_price;

	
	private final double bid_size;

	
	public Quote(String symbol_id, Instant time_exchange, Instant time_coinapi, double ask_price, double ask_size, double bid_price, double bid_size) {

		this.symbol_id = symbol_id;

		this.time_exchange = time_exchange;

		this.time_coinapi = time_coinapi;

		this.ask_price = ask_price;

		this.ask_size = ask_size;

		this.bid_price = bid_price;

		this.bid_size = bid_size;

	}
	
	
	public String get_symbol_id() {

		return symbol_id;
	
}
	
	
public Instant get_time_exchange() {

		return time_exchange;
	
}
	
	
public Instant get_time_coinapi() {

		return time_coinapi;
	
}
	
	
public double get_ask_price() {
	
	return ask_price;
	
}
	
	
public double get_ask_size() {

		return ask_size;
	
}
	
	
public double get_bid_price() {
	
	return bid_price;
	
}
	
	
public double get_bid_size() {

		return bid_size;
	
}
	
	
public boolean has_last_trade() {

		return false;
	
}
	

}
