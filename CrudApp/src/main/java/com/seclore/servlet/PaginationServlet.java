package com.seclore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seclore.entity.Product;
import com.seclore.entity.ProductDAO;

/**
 * Servlet implementation class PaginationServlet
 */
@WebServlet("/PaginationServlet")
public class PaginationServlet extends HttpServlet {
	
	private int pageSize = 5;
	private int cursor = 0;
	private int rows=0;
	
	private ProductDAO dao = new ProductDAO();
	
	@Override
	public void init() throws ServletException {
		rows = dao.fetchAll().size();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if (page!=null) {
			if (page.equals("next")) {
				cursor += pageSize;
			} else if (page.equals("prev")) {
				cursor -= pageSize;
			}

		} else {
			cursor = 0;

		}
		List<Product> products =  dao.fetch(cursor, pageSize);
		request.setAttribute("products", products);
		request.getRequestDispatcher("viewPaginatedProducts.jsp").forward(request, response);
		
	}

}
