package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;

/**
 * Servlet implementation class ItemServlet
 */
public class ItemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String URL_CART = Locale.getSystemValue("URL_CART");
	private CartServiceImpl cService = new CartServiceImpl();
	private ItemServiceImpl iService = new ItemServiceImpl();

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart = iService.addItemToCart((Cart) request.getSession()
				.getAttribute("cart"), iService.newItem(
				Integer.parseInt(request.getParameter("id")),
				Integer.parseInt(request.getParameter("number"))));
		cart.updateTotal();
		request.getSession().setAttribute("cart", cart);

		// request.getSession().setAttribute("cart",
		// iService.addItemToCart((Cart)request.getSession().getAttribute("cart"),
		// iService.newItem(Integer.parseInt(request.getParameter("id")),
		// Integer.parseInt(request.getParameter("number")))));
		response.sendRedirect(request.getContextPath() + URL_CART);
	}

}
