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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((ls == null) ? 0 : ls.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringBean other = (StringBean) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (ls == null) {
			if (other.ls != null)
				return false;
		} else if (!ls.equals(other.ls))
			return false;
		return true;
	}
}
