package cn.oocl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Locale {
	private static Properties prop;

	static {
		prop = new Properties();
		try {
//			String locale = "";
//			InputStream configIS = new FileInputStream(new File("config/app.config"));
//			Properties configProp = new Properties();
//			configProp.load(configIS);
//			locale = configProp.get("locale").toString().trim();
			InputStream inputStream = new FileInputStream(new File("config/app.config"));
			prop.load(inputStream);
		} catch (Exception e) {
			System.out.println("fail to get locale value");
		}
	}

	public static String getValue(String key) {
		return prop.get(key).toString();
	}
}
