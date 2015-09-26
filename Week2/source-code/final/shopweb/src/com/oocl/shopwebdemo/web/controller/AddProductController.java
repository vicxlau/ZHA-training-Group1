package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.Product;
import com.oocl.shopwebdemo.service.IProductEditService;
import com.oocl.shopwebdemo.service.ProductEditServiceImpl;

public class AddProductController extends HttpServlet {

	private IProductEditService productEditService = new ProductEditServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
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
			
		} catch(Exception e) {
			
			request.setAttribute("showErrorMsg", true);
			request.setAttribute("addProductSuccess", false);
			request.setAttribute("errorMsg", e.getMessage());
		}


		request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
	}
}
