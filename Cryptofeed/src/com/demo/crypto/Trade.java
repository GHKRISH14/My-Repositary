package com.demo.crypto;

import java.time.Instant;


public class Trade {

	private final String symbol_id;
	private final Instant time_exchange;
	private final Instant time_coinapi;
	private final String uuid;
	private final double price;
	private final double size;
	private final Taker_side taker_side;
	
	public Trade(String symbol_id, Instant time_exchange, Instant time_coinapi, String uuid, double price, double size, Taker_side taker_side) {
		this.symbol_id = symbol_id;
		this.time_exchange = time_exchange;
		this.time_coinapi = time_coinapi;
		this.uuid = uuid;
		this.price = price;
		this.size = size;
		this.taker_side = taker_side;
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
	
	public String get_uuid() {
		return uuid;
	}
	
	public double get_price() {
		return price;
	}
	
	public double get_size() {
		return size;
	}
	
	public Taker_side get_taker_side() {
		return taker_side;
	}
	
}
