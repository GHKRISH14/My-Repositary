package com.demo.crypto;

import java.time.Instant;

public class Timedata {
	
	private final Instant time_period_start;
	
	private final Instant time_period_end;
	
	private final Instant time_open;
	
	private final Instant time_close;
	private final double price_open;
	private final double price_high;
	private final double price_low;
	private final double price_close;
	private final double volume_traded;
	
	private final int trades_count;
	
	public Timedata(
		Instant time_period_start, Instant time_period_end, Instant time_open, Instant time_close,
		double price_open, double price_high, double price_low, double price_close, double volume_traded, int trades_count
	) {
		this.time_period_start = time_period_start;
		this.time_period_end = time_period_end;
		this.time_open = time_open;
		this.time_close = time_close;
		this.price_open = price_open;
		this.price_high = price_high;
		this.price_low = price_low;
		this.price_close = price_close;
		this.volume_traded = volume_traded;
		this.trades_count = trades_count;
	}
	
	public Instant get_time_period_start() {
		return time_period_start;
	}
	
	public Instant get_time_period_end() {
		return time_period_end;
	}
	
	public Instant get_time_open() {
		return time_open;
	}
	
	public Instant get_time_close() {
		return time_close;
	}
	
	public double get_price_open() {
		return price_open;
	}
	
	public double get_price_high() {
		return price_high;
	}
	
	public double get_price_low() {
		return price_low;
	}
	
	public double get_price_close() {
		return price_close;
	}
	
	public double get_volume_traded() {
		return volume_traded;
	}
	
	public int get_trades_count() {
		return trades_count;
	}
	
}
