package com.shop.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.product.Constants;
import com.shop.product.model.Product;
import com.shop.product.service.ImageEncodeService;
import com.shop.product.service.ProductService;
import com.shop.product.service.impl.ImageEncodeServiceImpl;
import com.shop.product.service.impl.ProductServiceImpl;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 4184201747939204477L;

	private ProductService productService;
	private ImageEncodeService imageEncodeService;

	@Override
	public void init() throws ServletException {
		super.init();

		productService = new ProductServiceImpl();
		imageEncodeService = new ImageEncodeServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action") == null ? "" : req.getParameter("action");

		req.setAttribute("imageEncodeService", imageEncodeService);

		switch (action) {
		case "Edit":
			proceedToEditPage(req, resp);
			break;
		case "LoadMore":
			loadMoreProducts(req, resp);
			break;
		case "Search":
		default:
			showDataGrid(req, resp);
			break;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] ids = req.getParameterValues("id");

		try {
			productService.deleteProducts(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void proceedToEditPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String selectedId = req.getParameter("id");
		boolean showSaveButton = selectedId != null;
		boolean showAddButton = selectedId == null;

		Product product = new Product();
		if (selectedId != null) {
			try {
				product = productService.getProduct(Integer.parseInt(selectedId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		req.setAttribute("showSaveButton", showSaveButton);
		req.setAttribute("showAddButton", showAddButton);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/WEB-INF/JSP/edit-product.jsp").forward(req, resp);
	}

	private void loadMoreProducts(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setProductsAttribute(req);
		req.getRequestDispatcher("/WEB-INF/JSP/fragment/product-items.jsp").forward(req, resp);
	}

	private void showDataGrid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setProductsAttribute(req);
		req.setAttribute("query", getQueryParameter(req));

		int maxPageNumber = 1;
		try {
			maxPageNumber = (int) Math.ceil((double) productService.getProductsSize(getQueryParameter(req))
					/ Constants.MAX_PRODUCTS_PER_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("maxPageNumber", maxPageNumber);

		req.getRequestDispatcher("/WEB-INF/JSP/dashboard.jsp").forward(req, resp);
	}

	private void setProductsAttribute(HttpServletRequest req) {
		int page = req.getParameter("page") == null ? 0 : Integer.parseInt(req.getParameter("page")) - 1;
		long offset = page * Constants.MAX_PRODUCTS_PER_PAGE;
		int limit = Constants.MAX_PRODUCTS_PER_PAGE;

		List<Product> products = null;
		try {
			products = productService.getProducts(getQueryParameter(req), offset, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("products", products);
	}

	private String getQueryParameter(HttpServletRequest req) {
		return req.getParameter("query") == null ? "" : req.getParameter("query");
	}
}
