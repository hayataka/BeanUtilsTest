package com.github.hayataka.spring.maptobeantransformer;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.springframework.beans.BeanInstantiationException;

import com.github.hayataka.entity.DateBean;
import com.github.hayataka.entity.Person;
import com.github.hayataka.entity.StringBean;



public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

	//● 文字列のクラスからのインスタンス化確認
		String className = "com.github.hayataka.entity.Person";
		// ここは多分 ConcurrentHashMapなどで保持
		Class<?> clazz = Class.forName(className);


		Person instantiate = (Person)instantiate(clazz); // ぶっちゃけこれだけならSpring不要




		// this Area IS  From    http://www.ne.jp/asahi/hishidama/home/tech/apache/commons/beanutil.html

		DateBean   src = new DateBean();
		StringBean dst = new StringBean();

		// srcに現在日時をセット
		src.setDate(new Date());


	//●BeanUtilsを使ってdstへコピー
		BeanUtils.copyProperties(dst, src);
		System.out.println(dst.getDate());


	//●独自BeanUtilsBeanを作ってデフォルト状態でdstへコピー
		BeanUtilsBean bu = new BeanUtilsBean(
					new ConvertUtilsBean(), 	//独自コンバーターを登録する為に新しいインスタンスを生成
					BeanUtilsBean.getInstance().getPropertyUtils() //デフォルトのPropertyUtilsBeanを共用
				);
		bu.copyProperties(dst, src);
		System.out.println(dst.getDate());


	//●独自BeanUtilsBeanに独自コンバーターをセットしてdstへコピー
		bu.getConvertUtils().register(
					new SampleStringConverter(), //独自コンバーターを
					String.class		  //String型への変換として登録
				);
		bu.copyProperties(dst, src);
		System.out.println(dst.getDate());


	//●大元のBeanUtilsには影響してないことを確認
		BeanUtils.copyProperties(dst, src);
		System.out.println(dst.getDate());

		System.out.println("OK");
	}


	public static <T> T instantiate(Class<T> clazz) throws BeanInstantiationException {

		if (clazz.isInterface()) {
			throw new RuntimeException("Specified class is an interface");
		}
		try {
			return clazz.newInstance();
		}
		catch (InstantiationException ex) {
			throw new RuntimeException("Is it an abstract class?", ex);
		}
		catch (IllegalAccessException ex) {
			throw new RuntimeException("Is the constructor accessible?", ex);
		}
	}

}
