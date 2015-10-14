package com.oocl.shopwebdemo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import com.oocl.shopwebdemo.util.Locale;

public class LoginFilter implements Filter {

	private static final String URL_LOGIN = Locale.getSystemValue("URL_LOGIN");
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 在此处完成用户登录的判断
		HttpServletRequest rs = (HttpServletRequest) request;
		if (rs.getSession().getAttribute(Locale.getSystemValue("session_customer_attr")) == null) {
//			rs.setAttribute("url", rs.getContextPath()+"/"+rs.getServletPath()+"?"+rs.getQueryString());
			String url = rs.getServletPath()+ ((rs.getQueryString()==null)?"":("?"+rs.getQueryString()));
			rs.getSession().setAttribute("url", url);
//			rs.getRequestDispatcher("/login.jsp").forward(request,response);
			((HttpServletResponse)response).sendRedirect(rs.getContextPath()+URL_LOGIN);
		} else {
			chain.doFilter(request,response);
		}

		System.out.println("------doFilter------");

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("------init------");
	}

}
