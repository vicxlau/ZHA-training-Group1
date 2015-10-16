package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;

// @WebServlet("/AccountServlet") xml配置
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = Locale.getSystemValue("URL_HOME");
	private static final String URL_LOGIN = Locale.getSystemValue("URL_LOGIN");
	private static final String URL_BACKEND = Locale.getSystemValue("URL_BACKEND_HOME");
	private IAccountService accountService = new AccountServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * get方法临时用来处理内网中页面与页面之前的跳转,后面学了Spring MVC替换
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		request.getRequestDispatcher("/WEB-INF/" + url).forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1: 获取UI页面的数据
//		String name = request.getParameter("inputEmail").toString();
//		String pass = request.getParameter("inputPassword").toString();
//		Account acc = new Account(name, pass);
		// User user =new User(name,pass);

		// error?

		Customer c = accountService.login(new Account(request.getParameter("inputEmail"), request.getParameter("inputPassword")));

		// 2: 根据需求调用service
		// 3: 跳转到目标页面

		if (c.getUser().isLogin()) {
			// 登录成功,跳转到后台首页(登录的个人数据要存储到session中)
			request.getSession().setAttribute(Locale.getSystemValue("session_customer_attr"),c);
			
//			for(Address a : c.getAddrList())
//		        System.out.format("%d : %s +++  %s +++ \n", a.getAddr_id(), a.getRecipient_name() , a.getRecipient_address());
			// request.getSession().setAttribute("name", name);
//			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index-in.jsp");
			 RequestDispatcher dispatcher ;
			 try{
				 dispatcher = request.getRequestDispatcher((String) request.getSession().getAttribute("url"));
				 dispatcher.forward(request, response);
			 }catch(Exception e){
				 if(c.getUser().getUser().getRoleId() == Integer.parseInt(Locale.getSystemValue("admin_role_id"))){
					 dispatcher = request.getRequestDispatcher(URL_BACKEND);
				 	 dispatcher.forward(request, response);
				 }else{ 
					 dispatcher = request.getRequestDispatcher(URL_HOME);
					 dispatcher.forward(request, response);
				 }
			 }

			// 访问WEB-INF文件夹的页面只能用forward方式
			// response.sendRedirect(request.getContextPath() +
			// "/WEB-INF/index.jsp");
		} else {
			// 登录失败,重新跳到登录页面,并且显示错误消息
			request.setAttribute("error", "FAILLLLLL");
//			登录失败!
			// 重定向,让客户在去访问 login.jsp, 如果当前的Servlet与目标页面Login有数据共享则采用forward方式
			// response.sendRedirect(request.getContextPath() + "/login.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(URL_LOGIN);
			dispatcher.forward(request, response);
		}
	}
}
