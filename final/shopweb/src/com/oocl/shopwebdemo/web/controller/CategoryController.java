package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.*;
import com.oocl.shopwebdemo.service.*;
import com.oocl.shopwebdemo.util.Locale;

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_HOME = Locale.getSystemValue("URL_HOME");
	private static final String URL_ERROR = Locale.getSystemValue("URL_ERROR");
	private static final String URL_BACKEND = Locale.getSystemValue("URL_BACKEND_HOME");
	private static final String URL_ADD_CATEGORY = Locale.getSystemValue("URL_BACKEND_ADD_CATEGORY");
	private static final String URL_QUERY_CATEGORY = Locale.getSystemValue("URL_BACKEND_QUERY_CATEGORY");
	private static final String URL_UPDATE_CATEGORY = Locale.getSystemValue("URL_BACKEND_UPDATE_CATEGORY");
	private static final String URL_DELETE_CATEGORY = Locale.getSystemValue("URL_BACKEND_DELETE_CATEGORY");
	private ICategoryService cService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (isLogin(request)) {
			String action = request.getParameter("action");
			try {
				switch (action) {
				case "addCategory":
					request.getRequestDispatcher(URL_ADD_CATEGORY).forward(request, response);
					break;
				case "queryCategory":
					request.getRequestDispatcher(URL_QUERY_CATEGORY).forward(request, response);
					break;
				case "updateCategory":
					showCategoryByID(request,response);
					break;
				case "deleteCategory":
					showDeletePage(request,response);
					break;
				}
			} catch (Exception e) {
				error(request,response,e);
			}
		} else {
			request.getRequestDispatcher(URL_HOME).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// vicx- add role checking
		if (isLogin(request)) {
			String action = request.getParameter("action");
			try {
				switch (action) {
				case "add":
					//done
					addCategory(request, response);
					break;
				case "update":
					updateCategory(request, response);
					break;
				case "delete":
					deleteCategory(request, response);
					break;
				case "query":
					//done
					queryCategory(request, response);
					break;
				}
			} catch (Exception e) {
				error(request,response,e);
			}
		} else {
			request.getRequestDispatcher(URL_HOME).forward(request, response);
		}
	}

	private void error(HttpServletRequest request,HttpServletResponse response, Exception e) throws ServletException, IOException {
		System.out.println("errorMsg: "+e);
		request.setAttribute("errorMsg", e);
		//not work
		request.getRequestDispatcher(URL_ERROR).forward(request,response);
	}

	private void showDeletePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("category", cService.getCategoryByIndex(Integer.parseInt(request.getParameter("id"))));		
		request.getRequestDispatcher(URL_DELETE_CATEGORY).forward(request, response);
	}
	
	private void showCategoryByID(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		Category c = cService.getCategoryByIndex(Integer.parseInt(request.getParameter("id")));
//		request.setAttribute("category",c);
		request.setAttribute("category", cService.getCategoryByIndex(Integer.parseInt(request.getParameter("id"))));
		request.getRequestDispatcher(URL_UPDATE_CATEGORY).forward(request, response);
	}

	private void addCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		cService.addCategory(new Category(request.getParameter("type"),
				Boolean.parseBoolean(request.getParameter("hot"))));
		request.setAttribute("addCategorySuccess", true);
		request.getRequestDispatcher(URL_BACKEND).forward(request,response);
	}

	private void updateCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		cService.updateCategory(new Category(Integer.parseInt(request.getParameter("id")), 
				request.getParameter("type"),
				Boolean.parseBoolean(request.getParameter("hot"))));
		request.setAttribute("updateCategorySuccess", true);
		request.getRequestDispatcher(URL_BACKEND).forward(request,response);
	}
	
	private void deleteCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		cService.deleteCategory(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("deleteCategorySuccess", true);
		request.getRequestDispatcher(URL_BACKEND).forward(request,response);
	}
	
	private void queryCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categoryList",cService.getCategoryByKeyword(request.getParameter("keyword"),
				Integer.parseInt(request.getParameter("pageSize")),
				Integer.parseInt(request.getParameter("pageNum"))
				));
		request.getRequestDispatcher(URL_QUERY_CATEGORY).forward(request,response);
	}

	private boolean isValidParam(HttpServletRequest request, String param) {
		return (request.getParameter(param) != null);
	}

	private boolean isLogin(HttpServletRequest request) {
		return (request.getSession().getAttribute(Locale.getSystemValue("session_customer_attr")) != null);
	}

}
