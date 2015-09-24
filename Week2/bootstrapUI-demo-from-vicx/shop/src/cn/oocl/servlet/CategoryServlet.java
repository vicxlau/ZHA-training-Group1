package cn.oocl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oocl.model.Category;
import cn.oocl.service.CategoryServiceImpl;

/* 访问当前Servlet的URL地址*/
//@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CategoryServiceImpl cs;
	
    public CategoryServlet() {
        super();
        cs = new CategoryServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		switch (request.getParameter("actionType").toUpperCase()){
		case "SAVE":
			save(request);break;
		case "QUERY":
			query(request);break;
		case "UPDATE":
			update(request);break;
		case "DELETE":
			delete(request);break;
		}
	}
	private void save(HttpServletRequest request){
		Category c = new Category(request.getParameter("type"),Boolean.parseBoolean(request.getParameter("hot")));
		cs.save(c);
	}

	private void query(){
		cs.query();
	}
	
	private void query(HttpServletRequest request){
		cs.query(request.getParameter("keyword"));
	}
	private void update(HttpServletRequest request){
		Category c = new Category(Integer.parseInt(request.getParameter("id")),request.getParameter("type"),Boolean.parseBoolean(request.getParameter("hot")));
		cs.update(c);
	}
	
	private void delete(HttpServletRequest request){
		cs.delete(Integer.parseInt(request.getParameter("id")));
	}
}
