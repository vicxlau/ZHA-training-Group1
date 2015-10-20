package com.oocl.shopwebdemo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

public class Logger {
	private static PrintWriter out;
	public static String INFO = "INFO";
	public static String WARNING = "WARNING";
	public static String ERROR = "ERROR";
	
	static {
		try {
			out = new PrintWriter(
					new BufferedWriter(
							new FileWriter(ConfigReader.getSystemValue("log-file-location"), true)));
		} catch (Exception e) {
			System.out.println("fail to initial logger");
		}
	}

	public static void log(String type, String msg){
	    out.println(String.format("%s [%s] %s",getDateTime(), type, msg));
	       out.flush();
	}
		public static void close() {
		out.close();
	}
	
	private static String getDateTime(){
		Date date= new Date();
		return (new Timestamp(date.getTime())).toString();
	}
//	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
//
//		PrintWriter out = new PrintWriter(Locale.getSystemValue("log-file-location"));
//		OutputStream inputStream = new OutputStream();
//		PrintWriter out = res.getWriter();
//		res.setContentType("text/plain");
//
//		Enumeration<String> parameterNames = req.getParameterNames();
//
//		while (parameterNames.hasMoreElements()) {
//
//			String paramName = parameterNames.nextElement();
//			out.write(paramName);
//			out.write("n");
//
//			String[] paramValues = req.getParameterValues(paramName);
//			for (int i = 0; i < paramValues.length; i++) {
//				String paramValue = paramValues[i];
//				out.write("t" + paramValue);
//				out.write("n");
//			}
//
//		}
//
//		out.close();
//
//	}
}
