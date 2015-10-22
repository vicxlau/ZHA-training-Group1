package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oocl.shopwebdemo.model.Account;
import com.oocl.shopwebdemo.model.Customer;
import com.oocl.shopwebdemo.service.IAccountService;
import com.oocl.shopwebdemo.util.ConfigReader;

// @WebServlet("/AccountServlet") xml配置
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = ConfigReader.getSystemValue("URL_HOME");
	private static final String URL_LOGIN = ConfigReader.getSystemValue("URL_LOGIN");
	private static final String URL_BACKEND = ConfigReader.getSystemValue("URL_BACKEND_HOME");
	private IAccountService accountService = null;
	private ApplicationContext context = null;
	
	
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	
	
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		System.out.println("-----init----");
		System.out.println("this:" + this);
		System.out.println("servletcontext:" + this.getServletContext());
		// Spring提供了一个工具类,通过此工具类去Application内置对象中获取spring配置文件(保证配置文件只是在启动的时候加载一次)
		context = WebApplicationContextUtils.getWebApplicationContext(this
				.getServletContext());
		accountService = (IAccountService) context.getBean("accountService");
		
	}
	
	
	
	public AccountController() {
		super();
	
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		String url = request.getParameter("url");
//		request.getRequestDispatcher("/WEB-INF/" + url).forward(request,response);
		String locale = request.getParameter("locale");
		if(locale!=null){
//			((Customer)rs.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr"))).getUser()==null
			Customer c = (Customer)request.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr"));
			if(c!=null)c.setLocale(locale);
			else request.getSession().setAttribute(ConfigReader.getSystemValue("session_customer_attr"),new Customer());
//			request.getSession().setAttribute("javax.servlet.jsp.jstl.fmt.locale.session", "ko-KR");
		}
		request.getRequestDispatcher(URL_LOGIN).forward(request,response);
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

		if (c.getAccount().isLogin()) {
			// 登录成功,跳转到后台首页(登录的个人数据要存储到session中)
			request.getSession().setAttribute(ConfigReader.getSystemValue("session_customer_attr"),c);
			
//			for(Address a : c.getAddrList())
//		        System.out.format("%d : %s +++  %s +++ \n", a.getAddr_id(), a.getRecipient_name() , a.getRecipient_address());
			// request.getSession().setAttribute("name", name);
//			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index-in.jsp");
			 RequestDispatcher dispatcher ;
			 try{
				 dispatcher = request.getRequestDispatcher((String) request.getSession().getAttribute("url"));
				 dispatcher.forward(request, response);
			 }catch(Exception e){
				 if(c.getAccount().getUser().getRoleId() == Integer.parseInt(ConfigReader.getSystemValue("admin_role_id"))){
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