package com.github.hayataka.spring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;

import com.github.hayataka.entity.Person;

public class DataBinderSampleTest {

	private static final Log log = LogFactory.getLog(DataBinderSampleTest.class);

	@Test
	public void testBindSimple() {
		// simple
		MutablePropertyValues values = new MutablePropertyValues();
		values.addPropertyValue("id", "7839");
		values.addPropertyValue("name", "KING");
		values.addPropertyValue("birthDay", new Date());

		DataBinderSample binder = new DataBinderSample();
		Person dest = new Person();
		binder.bind(values, dest);
		
		log.debug(dest);
		assertThat(dest, is(notNullValue()));
		assertThat(dest.getId(), is(7839));
		assertThat(dest.getName(), is("KING"));
		assertThat(dest.getBirthDay(), is(notNullValue()));
		
	}

	@Test
	public void testBindMap() {
		// simple
		Map<String, Object> values = new HashMap<>();
		values.put("id", "7839");
		values.put("name", "KING");
		values.put("birthDay", new Date());

		DataBinderSample binder = new DataBinderSample();
		Person dest = new Person();
		binder.bind(values, dest);
		
		log.debug(dest);
		assertThat(dest, is(notNullValue()));
		assertThat(dest.getId(), is(7839));
		assertThat(dest.getName(), is("KING"));
		assertThat(dest.getBirthDay(), is(notNullValue()));
		
	}
}
