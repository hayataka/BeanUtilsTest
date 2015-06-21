package com.github.hayataka.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * 一番標準的な、ネットで検索してよく出てくるタイプのapache.commonsのBeanUtilsの利用方法
 * 
 * @author hayakawatakahiko
 */
public class NormalBeanUtil {

	/**
	 * よくある一般的な使い方。型変換等は初期設定のまま.
	 * 
	 * @param dst
	 *            コピー先
	 * @param src
	 *            コピー元
	 */
	public void copyProps(final Object dst, final Object src) {
		try {
			BeanUtils.copyProperties(dst, src);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException("beanUtils execution error.", e);
		}
	}

	/**
	 * StaticなBeanUtilsに対して、converterを登録する、<b>NG, 誤った</b>使い方.
	 * 
	 * @param dst
	 *            コピー先
	 * @param src
	 *            コピー元
	 */
	public void copyNgUse(final Object dst, final Object src) {
		// 独自コンバーターを String型への変換として登録
		ConvertUtils.register(new DateToStringConverter(), String.class);
		copyProps(dst, src);

		// これを使用するならば、 そもそもBeanUtilsBeanを用いてインスタンスを別にすること
		// 　ConvertUtils.deregister(String.class);
	}

	public void copyOkUse(final Object dst, final Object src) {

		// ●独自BeanUtilsBeanに独自コンバーターをセットしてdstへコピー
		BeanUtilsBean bu = new BeanUtilsBean();
		bu.getConvertUtils().register(new DateToStringConverter(), String.class);
		try {
			bu.copyProperties(dst, src);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException("copyOkUse execution error.", e);
		}

	}

}
