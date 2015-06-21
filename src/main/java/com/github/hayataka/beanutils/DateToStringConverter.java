package com.github.hayataka.beanutils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateToStringConverter implements Converter  {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		// System.out.println(type); //当オブジェクトをString用として登録するので、typeは必ずStringのはず
		if (value == null) {
			return (T) null;
		} else if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			@SuppressWarnings("unchecked")
			final T ret = (T)sdf.format(value); 
			return ret;
		} else {
			@SuppressWarnings("unchecked")
			final T ret = (T)value.toString(); 
			return ret;
		}
	}
}
