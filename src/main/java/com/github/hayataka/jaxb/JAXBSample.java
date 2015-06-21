package com.github.hayataka.jaxb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 主にネットワーク転送のために、シリアライズするための処理. そういった要件が主のため、型を変換する機能の設定は（実装されているが、使用し難い）
 * 
 * @author hayakawatakahiko
 */
public class JAXBSample {
	/** log. **/
	private static final Log log = LogFactory.getLog(JAXBSample.class);

	/**
	 * Object to xml(String) often use for remote transport.
	 * 
	 * @param object
	 *            xml文字列化したいオブジェクト
	 * @return xml文字列
	 * @throws IOException 
	 */
	public String objectToString(final Object object) throws IOException  {
//		String xml = null;
//		JAXB.marshal(object, xml);
//		log.debug(xml);

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		JAXB.marshal(object, bs);
		byte[] byteArray = bs.toByteArray();
		String ret = new String(byteArray);
		bs.close();
		
		return ret;
	}

	public <T> T stringToObject(final String xml, Class<T> type) {
		T result = JAXB.unmarshal(new StringReader(xml), type);
		log.debug(result);
		return result;
	}
}
