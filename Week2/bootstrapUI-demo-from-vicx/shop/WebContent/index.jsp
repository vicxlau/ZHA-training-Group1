
	<%@ page import="java.util.*" %>
	<%@ page import="cn.oocl.service.*" %>
	<%@ page import="cn.oocl.model.*" %>
	<%
	//import javax.servlet.*;
	//import javax.servlet.http.*;

        CategoryServiceImpl cs = new CategoryServiceImpl();
        List<Category> lcat = cs.queryAll();

        ProductServiceImpl ps = new ProductServiceImpl();
        
//         List<Product> lpro= new ProductServiceImpl().queryAll();
        List<Product> lpro= ps.queryAll();
        
        String nextJSP = "/main.jsp";
        request.setAttribute("categoryList",lcat);
        request.setAttribute("productList",lpro);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
	%>
