package com.github.hayataka.spring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;

import com.github.hayataka.entity.DateBean;

public class BeanWrapperSampleTest {

	private static final Log log = LogFactory.getLog(BeanWrapperSampleTest.class);
	@Test
	public void testCopy() {
		 MutablePropertyValues mw = new MutablePropertyValues();
		 mw.add("ls[0]", "1");
		 mw.add("ls[1]", "2");
		 mw.add("ls[2]", "3");
		 mw.add("stringBean[0].date","hogehoge");
		 mw.add("stringBean[1].date","haaaaaaaaaaaaaogehoge");
		 mw.add("stringBean[2].date", "aaab");
		 mw.add("stringBean[0].ls[0]", "1");
		 mw.add("stringBean[0].ls[1]", "2");

		 DateBean dest = new DateBean();
		 
		 BeanWrapperSample binder = new BeanWrapperSample();
		 binder.bind(mw, dest);
		 
		 log.debug(dest);

		 assertThat(dest, is(notNullValue()));
		 assertThat(dest.getLs(), is(notNullValue()));
		 assertThat(dest.getLs().get(0), is("1"));
		 assertThat(dest.getLs().get(1), is("2"));
		 assertThat(dest.getLs().get(2), is("3"));
		 assertThat(dest.getStringBean().size(), is(3));
		 assertThat(dest.getStringBean().get(0).getDate(), is("hogehoge"));
		 assertThat(dest.getStringBean().get(0).getLs().get(1), is("2"));	 
	}
}
