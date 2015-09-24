package cn.oocl.servlet;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import cn.oocl.model.*;
import cn.oocl.service.*;

/* 访问当前Servlet的URL地址*/
//@WebServlet("/CategoryServlet")
public class OnLoad extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	List<Category> lcat = new ArrayList<Category>();
	
    public OnLoad() {
        super();
//        CategoryServiceImpl cs = new CategoryServiceImpl();
//        lcat = cs.queryAll();
//        String nextJSP = "/index_.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//        dispatcher.forward(request,response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
//	private void save(HttpServletRequest request){
//		Category c = new Category(request.getParameter("type"),Boolean.parseBoolean(request.getParameter("hot")));
//		cs.save(c);
//	}
//
//	private void query(){
//		cs.query();
//	}
//	
//	private void query(HttpServletRequest request){
//		cs.query(request.getParameter("keyword"));
//	}
//	private void update(HttpServletRequest request){
//		Category c = new Category(Integer.parseInt(request.getParameter("id")),request.getParameter("type"),Boolean.parseBoolean(request.getParameter("hot")));
//		cs.update(c);
//	}
//	
//	private void delete(HttpServletRequest request){
//		cs.delete(Integer.parseInt(request.getParameter("id")));
//	}
}
