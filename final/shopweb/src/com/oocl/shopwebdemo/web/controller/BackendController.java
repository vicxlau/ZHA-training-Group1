package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;

public class BackendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = Locale.getSystemValue("URL_HOME");
	private static final String URL_LOGIN = Locale.getSystemValue("URL_LOGIN");
	private static final String URL_ERROR = Locale.getSystemValue("URL_ERROR");
	private static final String URL_BACKEND = Locale.getSystemValue("URL_BACKEND_HOME");
	private static final String URL_HOME_REFRESH = Locale.getSystemValue("URL_BACKEND_HOME_REFRESH");

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		if (isLogin(request)) {
			if (isValidParam(request, "action")) {
				String action = (String) request.getParameter("action");
				switch (action) {
				case "homePageRefresh":
					request.getRequestDispatcher(URL_HOME_REFRESH).forward(request, response);
					break;
				}
			} else {
				request.getRequestDispatcher(URL_BACKEND).forward(request,response);
			}
		} else {
			request.getRequestDispatcher(URL_LOGIN).forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		if (isLogin(request)) {
			if (isValidParam(request, "action")) {
				String action = (String) request.getParameter("action");
				switch (action) {
					case "refreshData":
						homePageDataRefresh(request, response);
						break;
				}
			}
		}
	}

	private boolean isValidParam(HttpServletRequest request, String param) {
		return (request.getParameter(param) != null);
	}

	private boolean isLogin(HttpServletRequest request) {
		return (request.getSession().getAttribute(Locale.getSystemValue("session_customer_attr")) != null);
	}

	private void homePageDataRefresh(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("home_data",new ApplicationServiceImpl().refreshHomeData());
		request.getRequestDispatcher(URL_BACKEND).forward(request, response);
	}
}
