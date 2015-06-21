package com.github.hayataka.spring;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

public class DataBinderSample {

	private static final Log log = LogFactory.getLog(DataBinderSample.class);

	public void bind(Map<String, Object> src, final Object dest) {

		if (src == null || src.size() == 0) {
			return;
		}

		MutablePropertyValues values = new MutablePropertyValues();
		for(Entry<String, Object> entry : src.entrySet()) {
			values.add(entry.getKey(), entry.getValue());
		}
		bind(values, dest);
	}

	/**
	 * DataBinderを使用した物
	 * （注意：セキュリティ、脆弱性を考慮して使う必要あり）
	 * @see http://www.andore.com/money/trans/spring_ref_p6_ja.html
	 * @param src コピー元
	 * @param dest コピー先
	 */
	public void bind(MutablePropertyValues src, final Object dest) {

		DataBinder binder = new DataBinder(dest);
		binder.bind(src);
		close(binder);

	}
	
	private void close(DataBinder binder) {
		try {
			binder.close();
		} catch (BindException e) {
			List<ObjectError> allErrors = e.getAllErrors();
			for(ObjectError error : allErrors) {
				log.error(error.getObjectName());
			}
		}		
	}
}
