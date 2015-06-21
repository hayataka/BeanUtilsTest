package com.github.hayataka.entity;

import java.util.Date;
import java.util.List;

public class DateBean {
	private Date date;

	private String id;

	private List<String> ls;

	
	private List<StringBean> stringBean;
	
	

	public List<StringBean> getStringBean() {
		return stringBean;
	}

	public void setStringBean(List<StringBean> stringBean) {
		this.stringBean = stringBean;
	}

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
		final int maxLen = 10;
		return "DateBean [date=" + date + ", id=" + id + ", ls=" + (ls != null ? ls.subList(0, Math.min(ls.size(), maxLen)) : null) + ", stringBean="
				+ (stringBean != null ? stringBean.subList(0, Math.min(stringBean.size(), maxLen)) : null) + "]";
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
