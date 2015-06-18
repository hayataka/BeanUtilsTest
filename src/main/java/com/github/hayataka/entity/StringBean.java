package com.github.hayataka.entity;

import java.util.List;

public class StringBean {
	private String date;

	private List<String> ls;
	public List<String> getLs() {
		return ls;
	}

	public void setLs(List<String> ls) {
		this.ls = ls;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "StringBean [date=" + date + ", ls=" + (ls != null ? ls.subList(0, Math.min(ls.size(), maxLen)) : null)
				+ "]";
	}

	public void setDate(String dt) {
		date = dt;
	}

	public String getDate() {
		return date;
	}
}
