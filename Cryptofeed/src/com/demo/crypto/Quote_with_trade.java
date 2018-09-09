package com.demo.crypto;

import java.time.Instant;


public class Quote_with_trade extends Quote {
	
	private final Trade last_trade;
	
	public Quote_with_trade(
		String symbol_id, Instant time_exchange, Instant time_coinapi,
		double ask_price, double ask_size, double bid_price, double bid_size, Trade last_trade
	) {
		super(symbol_id, time_exchange, time_coinapi, ask_price, ask_size, bid_price, bid_size);
		this.last_trade = last_trade;
	}
	
	@Override public boolean has_last_trade() {
		return last_trade != null;
	}
	
	public Trade get_last_trade() {
		return last_trade;
	}
	
}
