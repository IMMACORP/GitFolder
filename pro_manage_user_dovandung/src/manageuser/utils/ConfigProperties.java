/**
 * Copyright(C) 2017 Luvina SoftWare
ConfigProperties.java, Jan 19, 2017 DoVanDung
 */
package manageuser.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ConfigProperties
 * 
 * @author
 *
 */
public class ConfigProperties {
	static private Map<String, String> data = new HashMap<String, String>();

	/**
	 *
	 */
	static {
		Properties prop = new Properties();
		try {
			prop.load(MessageProperties.class.getResourceAsStream(("/config.properties")));
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
	static public String getData(String key) {
		String string = "";
		if (data.containsKey(key)) {
			string = data.get(key);
		}
		return string;
	}
}
