package com.github.hayataka.beanutils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.fail;

public class DateToStringConverterTest {

	private static final Log log = LogFactory.getLog(DateToStringConverterTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * normal use.
	 */
	@Test
	public void testConvert() {

		DateToStringConverter converter = new DateToStringConverter();

		{
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(2015, 2, 11, 12, 34, 56); // 2015/03/11 12:34:56
													// (1month add )
			Date date = calendar.getTime();
			String ret = converter.convert(String.class, date);
			log.debug(ret);
			assertThat(ret, is(not(nullValue())));
			assertThat(ret, is("2015/03/11 12:34:56"));
		}

		{
			String ret = converter.convert(String.class, null);
			log.debug(ret);
			assertThat(ret, is(nullValue()));
		}

		{
			String ret = converter.convert(String.class, "12345");
			log.debug(ret);
			assertThat(ret, is("12345"));
		}
	}

	/**
	 * DateToStringConverter just use Date to String.
	 * <p>
	 * so other Class throws ClassCastException.
	 * </p>
	 */
	@Test
	public void illegalUse() {
		exception.expect(ClassCastException.class);
		DateToStringConverter converter = new DateToStringConverter();
		Integer ret = converter.convert(Integer.class, "12345");
		fail("ret must not get data,   ret:" + ret);
	}
}
