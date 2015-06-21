package com.github.hayataka.jaxb;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.github.hayataka.entity.DateBean;
import com.github.hayataka.entity.StringBean;

public class JAXBSampleTest {

	private static final Log log = LogFactory.getLog(JAXBSampleTest.class);

	@Test
	public void testObjectToString() throws IOException {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2015, 2, 11, 12, 34, 56); // 2015/03/11 12:34:56(1month add
												// )
		Date date = calendar.getTime();

		DateBean bean = new DateBean();
		bean.setDate(date);
		bean.setId("123444");
		List<String> ls = new ArrayList<>();
		ls.add("33");
		ls.add("44");
		ls.add("55");
		bean.setLs(ls);
		List<StringBean> stringBean = new ArrayList<>();
		StringBean sb = new StringBean();
		sb.setDate("2011/01/02");
		stringBean.add(sb);
		bean.setStringBean(stringBean);

		JAXBSample jaxbSample = new JAXBSample();
		final String serialized = jaxbSample.objectToString(bean);
		log.debug(serialized);
		assertThat(serialized, is(notNullValue()));

		// TODO matcherの書き方調べる
		assertThat(serialized.contains("dateBean"), is(true));

		DateBean after = jaxbSample.stringToObject(serialized, DateBean.class);
		log.debug(after);

		assertThat(after, is(notNullValue()));
		// equalsとhashcodeの実装しているクラスなので比較可能
		assertThat(bean, is(equalTo(after)));

	}
}
