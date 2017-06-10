/**
 * Copyright(C) 2017 Luvina SoftWare
MessageErrorProperties.java, Jan 19, 2017 DoVanDung
 */
package manageuser.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import manageuser.utils.MessageProperties;
/**
 * class MessageErrorProperties
 * @author dovandung
 *
 */
public class MessageErrorProperties {
	static private Map<String, String> data = new HashMap<String, String>();

	/**
	 *
	 */
	static {
		Properties prop = new Properties();
		try {
			prop.load(MessageProperties.class.getResourceAsStream(("/message_error_ja.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			data.put(key, prop.getProperty(key));
		}
	}

	/**
	 * getData from file properties
	 * 
	 * @param key
	 *            key
	 * @return String
	 */
	static public String getMessage(String key) {
		String string = "";
		if (data.containsKey(key)) {
			string = data.get(key);
		}
		return string;
	}

	/**
	 * getData from file properties
	 * 
	 * @param key
	 *            key
	 * @return String
	 */
	static public String getErrMessage(String key) {
		String string = "";
		if (data.containsKey(key)) {
			string = data.get(key);
		}
		return string;
	}
}
