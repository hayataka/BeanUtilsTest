package com.github.hayataka.beanutils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.github.hayataka.entity.DateBean;
import com.github.hayataka.entity.StringBean;

public class NormalBeanUtilTest {

	private static final Log log = LogFactory.getLog(NormalBeanUtilTest.class);

	/**
	 * 標準的な説明用.
	 * 
	 * @see http 
	 *      ://www.ne.jp/asahi/hishidama/home/tech/apache/commons/beanutil.html
	 * @throws Exception
	 */
	@Test
	public void successPattern() throws Exception {

		DateBean src = new DateBean();
		StringBean dst = new StringBean();

		// srcに現在日時をセット
		src.setDate(new Date());

		// ●BeanUtilsを使ってdstへコピー
		BeanUtils.copyProperties(dst, src);

		// ●独自BeanUtilsBeanを作ってデフォルト状態でdstへコピー
		// デフォルトのPropertyUtilsBeanを共用
		PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();
		// 独自コンバーターを登録する為に新しいインスタンスを生成
		BeanUtilsBean bu = new BeanUtilsBean(new ConvertUtilsBean(), propertyUtils);
		bu.copyProperties(dst, src);

		// ●独自BeanUtilsBeanに独自コンバーターをセットしてdstへコピー
		// 独自コンバーターをString型への変換として登録
		bu.getConvertUtils().register(new DateToStringConverter(), String.class);
		bu.copyProperties(dst, src);

		// ●大元のBeanUtilsには影響してないことを確認
		BeanUtils.copyProperties(dst, src);

		log.debug("OK");
	}

	/**
	 * BeanUtils.copyPropertiesをstaticで利用しているために発生する問題説明用.
	 * <p>
	 * SingleThreadでも、 （deRegisterを使用しなければ）問題となる
	 * </p>
	 */
	@Test
	public void ngPatternInSingleThread() {

		Calendar calendar = GregorianCalendar.getInstance();
		// 2015/03/11 12:34:56 (1month add )
		calendar.set(2015, 2, 11, 12, 34, 56);
		Date date = calendar.getTime();

		NormalBeanUtil beanUtil = new NormalBeanUtil();
		DateBean dateBean = new DateBean();
		StringBean stringBean = new StringBean();
		dateBean.setDate(date);
		beanUtil.copyProps(stringBean, dateBean);
		final String date1 = stringBean.getDate();
		log.debug("date1:" + date1);
		assertThat(date1, is("Wed Mar 11 12:34:56 JST 2015"));

		stringBean = new StringBean();
		beanUtil.copyNgUse(stringBean, dateBean);
		final String date2 = stringBean.getDate();
		log.debug("date2:" + date2);
		assertThat(date2, is("2015/03/11 12:34:56"));

		stringBean = new StringBean();
		beanUtil.copyProps(stringBean, dateBean);
		final String date3 = stringBean.getDate();
		log.debug("date3:" + date3);

		/**
		 * 
		 * date3 use copyProps (static method)
		 * 
		 * it must be Wed Mar 11 12:34:56 JST 2015 but ngUse method register
		 * another converter, it is 2015/03/11 12:34:56
		 */
		assertThat(date3, is(not("Wed Mar 11 12:34:56 JST 2015")));
		assertThat(date3, is("2015/03/11 12:34:56"));
	}

	/**
	 * BeanUtils.copyPropertiesをインスタンス経由で利用して問題を解消している説明用.
	 */
	@Test
	public void okPatternInSingleThread() {

		Calendar calendar = GregorianCalendar.getInstance();
		// 2015/03/11 12:34:56 (1month add )
		calendar.set(2015, 2, 11, 12, 34, 56);
		Date date = calendar.getTime();

		NormalBeanUtil beanUtil = new NormalBeanUtil();
		DateBean dateBean = new DateBean();
		StringBean stringBean = new StringBean();
		dateBean.setDate(date);
		beanUtil.copyProps(stringBean, dateBean);
		final String date1 = stringBean.getDate();
		log.debug("date1:" + date1);
		assertThat(date1, is("Wed Mar 11 12:34:56 JST 2015"));

		stringBean = new StringBean();
		beanUtil.copyOkUse(stringBean, dateBean);
		final String date2 = stringBean.getDate();
		log.debug("date2:" + date2);
		assertThat(date2, is("2015/03/11 12:34:56"));

		stringBean = new StringBean();
		beanUtil.copyProps(stringBean, dateBean);
		final String date3 = stringBean.getDate();
		log.debug("date3:" + date3);
		assertThat(date3, is("Wed Mar 11 12:34:56 JST 2015"));
		assertThat(date3, is(not("2015/03/11 12:34:56")));
	}
}
