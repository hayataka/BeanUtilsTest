package com.github.hayataka.jaxb;

import java.io.StringReader;
import java.util.Date;

import javax.xml.bind.JAXB;

import com.github.hayataka.entity.DateBean;

public class JaxBMain {

	public static void main(String[] args) {
		DateBean hoge = new DateBean();
		hoge.setDate(new Date());
		JAXB.marshal(hoge, System.out);

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<dateBean>" + "<date>2015-06-19T22:14:53.956+09:00</date>"
				+ "</dateBean>";

		DateBean after = JAXB.unmarshal(new StringReader(xml), DateBean.class);
		System.out.println("");

	}

}
