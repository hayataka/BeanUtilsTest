package com.github.hayataka.entity;

import java.util.Date;
import java.util.List;

public class DateBean {
	private Date date;

	private String id;

	private List<String> ls;

	public List<String> getLs() {
		return ls;
	}

	public void setLs(List<String> ls) {
		this.ls = ls;
	}

	public void setDate(Date dt) {
		date = dt;
	}

	@Override
	public String toString() {
		return "DateBean [date=" + date + ", id=" + id + ", ls=" + ls + "]";
	}

	public Date getDate() {
		return date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
