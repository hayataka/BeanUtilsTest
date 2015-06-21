package com.github.hayataka.spring.maptobeantransformer;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import com.github.hayataka.entity.DateBean;
import com.github.hayataka.entity.StringBean;

public class SpringDataBinderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//https://technology.amis.nl/2007/02/04/using-the-spring-databinder-to-map-strings-to-objects/
		// simple
		MutablePropertyValues values = new MutablePropertyValues();
		values.addPropertyValue("id", "7839");
		values.addPropertyValue("name", "KING");
		values.addPropertyValue("date", "2010/12/11");
//		values.addPropertyValue("date", new Date());

		StringBean employee = new StringBean();
		DataBinder binder = new DataBinder(employee);
		binder.bind(values);

		System.out.println(employee);


		try {
			binder.close();
		} catch (BindException e) {
			List<ObjectError> allErrors = e.getAllErrors();
			for(ObjectError error : allErrors) {
				System.out.println(error.getObjectName());
			}
		}


		DateBean dateBean = new DateBean();

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
// 出来たー
		BeanWrapperImpl bw = new BeanWrapperImpl(dateBean);
		bw.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		bw.registerCustomEditor(List.class, new CustomCollectionEditor(ArrayList.class));
		bw.setPropertyValue("date", "2010/12/11");

//		List<String> aa = new ArrayList<>();
//		aa.add("1");
//		aa.add("2");

		MutablePropertyValues mw = new MutablePropertyValues();
		mw.add("ls[0]", "1");
		mw.add("ls[1]", "2");
		mw.add("ls[2]", "3");
		mw.add("stringBean[0].date","hogehoge");
		mw.add("stringBean[1].date","haaaaaaaaaaaaaogehoge");	

bw.setAutoGrowNestedPaths(true);
bw.setAutoGrowCollectionLimit(100);
		bw.setPropertyValues(mw);


		System.out.println(dateBean);





	}

}
