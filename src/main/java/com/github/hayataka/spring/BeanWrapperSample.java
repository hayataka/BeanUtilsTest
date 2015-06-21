package com.github.hayataka.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class BeanWrapperSample {

	public void bind(MutablePropertyValues mw, final Object dest) {

		BeanWrapperImpl bw = new BeanWrapperImpl(dest);
		bw.registerCustomEditor(List.class, new CustomCollectionEditor(ArrayList.class));

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		bw.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		// 複数の日付フォーマットが必要な場合には、下記のように （クラスを新規作成するなり無名クラスなりで）overrideする
		// bw.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		// 		@Override
		// 		public void setAsText(String text) throws IllegalArgumentException {
		// 			super.setAsText(text);
		// 		}
		// })
		
		/**
		 * ネストしたプロパティにアクセスする場合には、 classloaderをサポートしないように除外すること
		 */
		bw.setAutoGrowNestedPaths(true);
		bw.setAutoGrowCollectionLimit(100);

		// コピー実行
		bw.setPropertyValues(mw);
	}
}
