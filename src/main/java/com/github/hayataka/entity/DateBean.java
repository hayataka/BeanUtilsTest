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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ls == null) ? 0 : ls.hashCode());
		result = prime * result + ((stringBean == null) ? 0 : stringBean.hashCode());
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
		DateBean other = (DateBean) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ls == null) {
			if (other.ls != null)
				return false;
		} else if (!ls.equals(other.ls))
			return false;
		if (stringBean == null) {
			if (other.stringBean != null)
				return false;
		} else if (!stringBean.equals(other.stringBean))
			return false;
		return true;
	}
}
