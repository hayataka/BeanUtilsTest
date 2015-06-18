package com.github.hayataka.entity;

public class StringBean {
	private String date;

	@Override
	public String toString() {
		return "StringBean [date=" + date + "]";
	}

	public void setDate(String dt) {
		date = dt;
	}

	public String getDate() {
		return date;
	}
}
