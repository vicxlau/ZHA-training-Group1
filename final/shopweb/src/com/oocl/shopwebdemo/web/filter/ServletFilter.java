package com.oocl.shopwebdemo.web.filter;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.tree.ExpandVetoException;

import org.apache.catalina.connector.Request;

import com.oocl.shopwebdemo.model.DataStorage;
import com.oocl.shopwebdemo.model.ProductCurrentVisiter;
import com.oocl.shopwebdemo.service.ApplicationServiceImpl;
import com.oocl.shopwebdemo.util.ConfigReader;
import com.oocl.shopwebdemo.util.Logger;

public class ServletFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override  //  /*
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest rs = (HttpServletRequest)request;
		String url = rs.getServletPath()+ ((rs.getQueryString()==null)?"":("?"+rs.getQueryString()));
		if(rs.getParameter("type")==null || !((String)rs.getParameter("type")).equals("webservice"))
			System.out.println("=========++++++++++++ servlet filer ("+url+")++++++++++===========");
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		DataStorage ds = (DataStorage)request.getServletContext().getAttribute("home_data");
//		request.setAttribute("categoryList", ds.getCategoryList());
//		
//		// update current visitor V2
////		HttpServletRequest rs = (HttpServletRequest)request;
////		Integer last_visiting_product = (Integer) rs.getSession().getAttribute(ConfigReader.getSystemValue("session-visiting-product"));
////		if(last_visiting_product!=null){
////			
////		}
//		
//		
////		// update current visitor
////		// deduce vistor count with last viewing product
////		HttpServletRequest rs = (HttpServletRequest)request;
////		String url = rs.getServletPath()+ ((rs.getQueryString()==null)?"":("?"+rs.getQueryString()));
//////		if(url.contains("retrievalServlet?action=product")){
////		if(!url.contains("type=webservice")){
////			ProductCurrentVisiter visitor = (ProductCurrentVisiter)rs.getServletContext().getAttribute(ConfigReader.getSystemValue("appication-current-visitor"));
////			if(rs.getSession().getAttribute(ConfigReader.getSystemValue("session-visiting-product"))!=null){
////				try{
////					Integer previous_view = (int) rs.getSession().getAttribute(ConfigReader.getSystemValue("session-visiting-product"));
////					new ApplicationServiceImpl().removeCurrentVisitor(null, previous_view);
////					previous_view = null;
////					rs.getSession().setAttribute(ConfigReader.getSystemValue("session-visiting-product"),previous_view);
////				}catch(Exception e){
////					
////				}finally{
////				}
////			}
////		}
//		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void handleRequest(ServletRequest req, ServletResponse res) throws IOException {

		Enumeration<String> parameterNames = req.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String msg = paramName + ": ";

			String[] paramValues = req.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				msg += paramValue + ",";
			}
			Logger.log(Logger.INFO, msg);
		}
	}

}
