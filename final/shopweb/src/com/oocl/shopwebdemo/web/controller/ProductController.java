package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.ConfigReader;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = ConfigReader.getSystemValue("URL_HOME");
	private static final String URL_ERROR= ConfigReader.getSystemValue("URL_ERROR");
	private static final String URL_ADD_PRODUCT = ConfigReader.getSystemValue("URL_BACKEND_ADD_PRODUCT");
	private static final String URL_UPDATE_PRODUCT = ConfigReader.getSystemValue("URL_BACKEND_PRODUCT");
	private IProductService productEditService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//vicx- add role checking
		if (request.getSession().getAttribute(ConfigReader.getSystemValue("session_customer_attr")) != null) {
			String action = request.getParameter("action");
			try {
				switch (action) {
					case "addProduct":
						request.getRequestDispatcher(URL_ADD_PRODUCT).forward(request,response);
						break;
					case "updateProduct":
						request.getRequestDispatcher(URL_UPDATE_PRODUCT).forward(request,response);
						break;
					case "add":
						addProduct(request, response);
						break;
					case "update":
						updateProduct(request, response);
						break;
				}
			} catch (Exception e) {
				request.setAttribute("errorMsg", e);
				request.getRequestDispatcher(URL_ERROR).forward(request,response);
			}
		}else{
			request.getRequestDispatcher(URL_HOME).forward(request,response);			
		}
	}

	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		productEditService.addProduct(
				new Product(
					request.getParameter("pro_name"),
					Double.parseDouble(request.getParameter("pro_price")),
					request.getParameter("pro_pic"),
					request.getParameter("pro_remark"),
					Double.parseDouble(request.getParameter("pro_adv")),
					Integer.parseInt(request.getParameter("cat_id"))
				)
		);
		request.setAttribute("addProductSuccess", true);
		request.getRequestDispatcher(URL_ADD_PRODUCT).forward(request,response);


//		request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
	}

	private void updateProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//vicx- change to update
		productEditService.addProduct(
				new Product(
					request.getParameter("pro_name"),
					Double.parseDouble(request.getParameter("pro_price")),
					request.getParameter("pro_pic"),
					request.getParameter("pro_remark"),
					Double.parseDouble(request.getParameter("pro_adv")),
					Integer.parseInt(request.getParameter("cat_id"))
				)
		);
		request.setAttribute("updateProductSuccess", true);
		request.getRequestDispatcher(URL_UPDATE_PRODUCT).forward(request,response);		
	}
}
