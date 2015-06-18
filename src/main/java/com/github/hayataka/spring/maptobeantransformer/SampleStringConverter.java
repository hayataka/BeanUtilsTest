package com.github.hayataka.spring.maptobeantransformer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class SampleStringConverter implements Converter  {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		// System.out.println(type); //当オブジェクトをString用として登録するので、typeは必ずStringのはず
		if (value == null) {
			return (T) null;
		} else if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return (T)sdf.format(value);
		} else {
			return (T)value.toString();
		}
	}



}
