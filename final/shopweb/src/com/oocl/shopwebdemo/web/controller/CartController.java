package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oocl.shopwebdemo.dao.*;
import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.CartServiceImpl;
import com.oocl.shopwebdemo.util.Locale;

/**
 * Servlet implementation class ItemServlet
 */
public class CartController extends HttpServlet {
	
	private static final long serialVersionUID = 844483551957629268L;
	private final String URL_CUSTOMER_BANK = Locale.getSystemValue("URL_CUSTOMER_BANK");
	private CartServiceImpl cService = new CartServiceImpl();
	
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action){
			case "place_order":
//				place_order(request,response);
				break;
		
		}
		this.doPost(request, response);
	}

//	private void place_order(HttpServletRequest request,
//			HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if(action.equals("save")){
			// 0: 从session获取获取当前的购物车
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			// 1: 获取配送信息 
//			cart.setName(request.getParameter("name"));
//			cart.setPhone(request.getParameter("phone"));
//			cart.setPost(request.getParameter("post"));
//			cart.setAddress(request.getParameter("address"));
//			cart.setRemark(request.getParameter("remark"));
			
			Customer c = (Customer) request.getSession().getAttribute(Locale.getSystemValue("session_customer_attr"));
			
			request.getSession().setAttribute("previousCart", 
				cService.save(cart,c.getUser().getUser().getId(),
					Integer.parseInt(request.getParameter("addr_id")),
					request.getParameter("remark")));
//			request.getSession().setAttribute("cart", null);
			request.getSession().removeAttribute("cart");
			// 4: 跳转到银行支付页面
			response.sendRedirect(request.getContextPath() + URL_CUSTOMER_BANK);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
