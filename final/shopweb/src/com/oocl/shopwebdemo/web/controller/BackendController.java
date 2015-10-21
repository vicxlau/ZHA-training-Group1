package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.util.ConfigReader;
import com.oocl.shopwebdemo.web.socket.NotificationSocket;

public class BackendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = ConfigReader.getSystemValue("URL_HOME");
	private static final String URL_LOGIN = ConfigReader.getSystemValue("URL_LOGIN");
	private static final String URL_ERROR = ConfigReader.getSystemValue("URL_ERROR");
	private static final String URL_BACKEND = ConfigReader.getSystemValue("URL_BACKEND_HOME");
	private static final String URL_HOME_REFRESH = ConfigReader.getSystemValue("URL_BACKEND_HOME_REFRESH");

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		if (isLogin(request)) {
			if (isValidParam(request, "action")) {
				String action = (String) request.getParameter("action");
				switch (action) {
				case "homePageRefresh":
					request.getRequestDispatcher(URL_HOME_REFRESH).forward(request, response);
					break;
				case "onSaleNotification":
					onSaleNotification(request, response);
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
					case "notification":
						notificationBroadcast(request, response);
						break;
				}
			}
		}
	}

	private void onSaleNotification(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int prod_id = Integer.parseInt(request.getParameter("prod_id"));
		Product product = new ProductServiceImpl().getProductByIndex(prod_id);
		String msg = String.format("<a href=\'http://lauvi-w7-2:8888/shopweb/retrievalServlet?action=product&id=%d \'><i class=\'fa fa-link\'></i> %s is now on sale ( % %d off)! Click to view ~~ </a>",
				product.getId(), product.getName(), product.getDiscount()); 
		executeBroadcast(msg);
		response.sendRedirect("http://lauvi-w7-2:8888/shopwebb/AccountServlet?url=productUpdate.jsp");
//		request.getRequestDispatcher("http://lauvi-w7-2:8888/shopwebb/AccountServlet?url=productUpdate.jsp").forward(request, response);	
	}
	
	private void executeBroadcast(String msg) throws IOException{
		NotificationSocket socket = new NotificationSocket();
		socket.onMessage(msg);
	}
	private void notificationBroadcast(HttpServletRequest request, HttpServletResponse response) throws IOException{
		executeBroadcast(request.getParameter("message"));
	}
	
	private boolean isValidParam(HttpServletRequest request, String param) {
		return (request.getParameter(param) != null);
	}

	private boolean isLogin(HttpServletRequest request) {
		return (request.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr")) != null
			&& ((Customer)request.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr"))).getAccount()!=null);
	}

	private void homePageDataRefresh(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().setAttribute("home_data",new ApplicationServiceImpl().refreshHomeData());
		request.getRequestDispatcher(URL_BACKEND).forward(request, response);
	}
}
