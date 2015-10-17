package com.oocl.shopwebdemo.util;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;

	static {
		prop = new Properties();
		try {
//			String locale = "";
//			InputStream configIS = new FileInputStream(new File("config/app.config"));
//			Properties configProp = new Properties();
//			configProp.load(configIS);
//			locale = configProp.get("locale").toString().trim();
			
			//TEST
//			File f = new File("test.config");
//			System.out.println(f.getAbsolutePath());
//			System.out.println(System.getProperty("user.dir"));
//			System.out.println(Locale.class.getResourceAsStream("./configg"));
//			System.out.println(Integer.parseInt("1000*60*60"));
//			f.createNewFile();
			
			//temp
			prop.load(ConfigReader.class.getResourceAsStream("/app.config"));
//			prop.load(Locale.class.getResourceAsStream("/app.properties"));
//			InputStream inputStream = new FileInputStream("app.config");
//			InputStream inputStream = new FileInputStream(new File("./configg"));
//			prop.load(inputStream);
		} catch (Exception e) {
			System.out.println("fail to get locale value");
		}
	}

	public static String getSystemValue(String key) {
//		return prop.get(key).toString();
		return prop.getProperty(key);
	}
}
