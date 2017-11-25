package com.bin.bean;

public class Counter {

	private static Long value = 0l;

	public static Long getValue() {
		return value;
	}
	
	public synchronized static void increment(Long miles) {
		value += miles;
	}
}
