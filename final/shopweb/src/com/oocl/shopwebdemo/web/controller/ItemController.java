package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.ConfigReader;

/**
 * Servlet implementation class ItemServlet
 */
public class ItemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String URL_CART = ConfigReader.getSystemValue("URL_CART");
	private ItemServiceImpl iService = new ItemServiceImpl();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null){
			// add item to cart
			addCartItemInSession(request, response);
		}
		else if(action.equals("deleteItem")){
			deleteCartItemInSession(request, response);
		}
		else if(action.equals("updateItem")){
			updateCartItemInSession(request, response);
		}
		else if(action.equals("undo")){
			undo(request, response);
		}
	}

	private void undo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		Cart cart = (Cart) session.getAttribute("cart-undo-buffer");
//		session.setAttribute("cart", cart);
		CartBuffer cartBuffer = (CartBuffer) request.getSession().getAttribute("cart-undo-buffer");
		if(cartBuffer != null && !cartBuffer.isEmpty()){
			Cart cart = cartBuffer.pop();
			session.setAttribute("cart", cart);
			PrintWriter writer = response.getWriter();
			writer.write(cart.getTotal().toString());
			writer.close();
		}
	}

	private void addCartItemInSession(HttpServletRequest request,
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
	
	private void deleteCartItemInSession(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		Cart currentCart = new Cart(cart);
//		request.getSession().removeAttribute("cart-undo-buffer");
		CartBuffer cartBuffer = (CartBuffer) request.getSession().getAttribute("cart-undo-buffer");
		if(cartBuffer==null){ 
			cartBuffer = new CartBuffer();
			request.getSession().setAttribute("cart-undo-buffer", cartBuffer);
		}
		cartBuffer.push(currentCart);
//		request.getSession().setAttribute("cart-undo-buffer", buffer);
		cart.deleteItem(Integer.parseInt(request.getParameter("id")));
		
//		no need as "request.getSession().getAttribute("cart")" is just an addr of session variable
//		request.getSession().setAttribute("cart", cart);
		
		PrintWriter writer = response.getWriter();
		writer.write(cart.getTotal().toString());
		writer.close();
//		RequestDispatcher dispatcher = request.getRequestDispatcher(URL_CART);
//		 dispatcher.forward(request, response);
	}
	
	private void updateCartItemInSession(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		cart.updateItem(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("num")));
		request.getSession().setAttribute("cart", cart);
//		RequestDispatcher dispatcher = request.getRequestDispatcher(URL_CART);
//		dispatcher.forward(request, response);
		PrintWriter writer = response.getWriter();
		writer.write(cart.getTotal().toString());
		writer.close();
	}

}
