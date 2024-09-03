package com.seclore.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seclore.entity.Product;
import com.seclore.entity.ProductDAO;

/**
 * Servlet implementation class CrudProductServlet
 */
@WebServlet("/CrudProductServlet")
public class CrudProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ProductDAO dao = new ProductDAO();
		System.out.println("IN DO GET");
		if (action.equals("fetchAll")) {
			List<Product> list = dao.fetchAll();
			request.setAttribute("listOfProducts", list);
			request.getRequestDispatcher("/viewProducts.jsp").forward(request, response);
		} else if (action.equals("add")) {
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setQuantity(quantity);
			int id = dao.add(product);
			request.setAttribute("productId", id);
			request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
		} else if (action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.delete(id);
			request.getRequestDispatcher("/CrudProductServlet?action=fetchAll").forward(request, response);
		} else if (action.equals("sort")) {
			List<Product> list = dao.fetchAll();
			if (request.getParameter("type").equals("lowToHigh")) {
				Collections.sort(list,(x,y)->x.getPrice()<y.getPrice() ? -1: x.getPrice()==y.getPrice()?0:1);
				request.setAttribute("listOfProducts", list);
				request.getRequestDispatcher("/viewProducts.jsp").forward(request, response);
			} else {
				Collections.sort(list,(x,y)->x.getPrice()<y.getPrice() ? 1: x.getPrice()==y.getPrice()?0:-1);
				request.setAttribute("listOfProducts", list);
				request.getRequestDispatcher("/viewProducts.jsp").forward(request, response);
			}
		} else if (action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Product p = dao.fetchById(id);
			request.setAttribute("product", p);
			request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
		} else if (action.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setId(id);
			dao.update(product);
			request.getRequestDispatcher("/CrudProductServlet?action=fetchAll").forward(request, response);
		}
	}

}
