package com.github.hayataka.util;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.github.hayataka.entity.Person;

public class InstanceUtilTest {

	private static final Log log = LogFactory.getLog(InstanceUtilTest.class);

	@Test
	public void testInstantiateStringOf() {

		InstanceUtil util = new InstanceUtil();
		// ● 文字列のクラスからのインスタンス化確認
		String className = "com.github.hayataka.entity.Person";
		Object object = util.instantiate(className);
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(Person.class)));
		log.debug(object);

		Person person = (Person) object;
		assertThat(person.getId(), is(0));
	}

	@Test
	public void testInstantinageClassOf() {

		InstanceUtil util = new InstanceUtil();
		Person person = util.instantiate(Person.class);
		assertThat(person, is(not(nullValue())));
		log.debug(person);
	}

}
