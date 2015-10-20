package com.oocl.shopwebdemo.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.connector.Request;

import com.oocl.shopwebdemo.model.DataStorage;
import com.oocl.shopwebdemo.util.Logger;

public class BasePageFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override  //  /*
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		DataStorage ds = (DataStorage)request.getServletContext().getAttribute("home_data");
		request.setAttribute("categoryList", ds.getCategoryList());
		handleRequest(request, response);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void handleRequest(ServletRequest req, ServletResponse res) throws IOException {

		Enumeration<String> parameterNames = req.getParameterNames();
		
				String loggerMsg = "";
				
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String msg = paramName + ": ";

			String[] paramValues = req.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
			String paramValue = paramValues[i];
				msg += paramValue + ",";

			}
		loggerMsg += msg;
		}
		if (loggerMsg != "") {
			Logger.log(Logger.INFO, loggerMsg);
			System.out.println(loggerMsg);
			
		}
	}

}
