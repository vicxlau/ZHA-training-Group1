package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.util.ConfigReader;

/**
 * Servlet implementation class ErrorHandler
 */
@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_ERROR_PAGE = ConfigReader.getSystemValue("URL_ERROR_PAGE");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// add log
		Exception e = (Exception)request.getAttribute("errorMsg");
		request.setAttribute("showErrorMsg", true);
		request.setAttribute("searchSuccess", false);
		request.setAttribute("errorMsg", e.getMessage());
		request.getRequestDispatcher(URL_ERROR_PAGE).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
