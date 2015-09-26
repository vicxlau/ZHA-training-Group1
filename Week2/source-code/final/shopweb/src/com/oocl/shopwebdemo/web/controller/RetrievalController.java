package com.oocl.shopwebdemo.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.Product;
import com.oocl.shopwebdemo.service.CategoryServiceImpl;
import com.oocl.shopwebdemo.service.ICategoryService;
import com.oocl.shopwebdemo.service.IProductEditService;
import com.oocl.shopwebdemo.service.ISearchService;
import com.oocl.shopwebdemo.service.ProductEditServiceImpl;
import com.oocl.shopwebdemo.service.SearchServiceImpl;

public class RetrievalController extends HttpServlet {

	private ICategoryService categoryService = new CategoryServiceImpl();
	private IProductEditService productService = new ProductEditServiceImpl();
	private ISearchService searchService = new SearchServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action) {
			case "home":
				home(request, response);
				// request.getRequestDispatcher("/index.jsp").forward(request,response);
				break;
			case "category":
				category(request, response);
				break;
			case "product":
				product(request, response);
				break;
			}

		} catch (Exception e) {

			request.setAttribute("showErrorMsg", true);
			request.setAttribute("searchSuccess", false);
			request.setAttribute("errorMsg", e.getMessage());
			System.out.println(e.getMessage());
			// vicx-- error page
			// request.getRequestDispatcher("/error.jsp").forward(request,response);
		}

	}

	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String pid = request.getParameter("id");
		if (pid != null) {
			try {
				int id= Integer.parseInt(pid);
				Product p = searchService.getProductByIndex(id);
				request.setAttribute("proName",p.getName());
				request.setAttribute("proPrice",p.getPrice());
				request.setAttribute("proRemark",p.getRemark());
				request.setAttribute("proPic",p.getPic());
				request.setAttribute("categoryList",categoryService.getAllCategory());
				productService.addProductVisitTime(id);
				request.getRequestDispatcher("/product.jsp").forward(request,response);
				
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void category(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String cid = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		if (cid != null) {
			try {
				request.setAttribute("catName", categoryService
						.getCategoryByIndex(Integer.parseInt(cid)).get(0)
						.getType());

				request.setAttribute(
						"cateResults",
						searchService.getProductInCategory(
								Integer.parseInt(cid),
								Integer.parseInt(pageNum)));
				request.getRequestDispatcher("/category.jsp").forward(request,
						response);

			} catch (Exception e) {
				try {
					request.setAttribute(
							"cateResults",
							searchService.getProductInCategory(
									Integer.parseInt(cid), 1));

					request.getRequestDispatcher("/category.jsp").forward(
							request, response);
				} catch (Exception ee) {
					throw ee;
				}
			}
		}

		// SearchProductsResult searchResults;
		// searchResults = searchService.searchProducts(
		// request.getParameter("keyword"),
		// Integer.parseInt(request.getParameter("pageSize")),
		// Integer.parseInt(request.getParameter("pageNum"))
		// );
		//
		// request.setAttribute("totalResultCount",
		// searchResults.getTotalResultCount());
		// request.setAttribute("pageSize", searchResults.getPageSize());
		// request.setAttribute("pageCount", searchResults.getPageCount());
		// request.setAttribute("pageNum", searchResults.getPageNum());
		// request.setAttribute("pageResults", searchResults.getPageResults());
		//
		// request.setAttribute("searchSuccess", true);

	}

	public void home(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("categoryList", categoryService.getAllCategory());
		request.setAttribute("productList", searchService.getHotProduct());
		request.setAttribute("advList", searchService.getAdvProduct());
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
