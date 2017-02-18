package com.shop.product.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.shop.product.model.Product;
import com.shop.product.service.FormatDateService;
import com.shop.product.service.ProductService;
import com.shop.product.service.impl.FormatDateServiceImpl;
import com.shop.product.service.impl.ProductServiceImpl;

@WebServlet("/edit-product")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 5MB
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 7107261517661909385L;

	private ProductService productService;
	private FormatDateService formatDateService;

	@Override
	public void init() throws ServletException {
		super.init();

		productService = new ProductServiceImpl();
		formatDateService = new FormatDateServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		Product product = null;

		if (action.equals("Save")) {
			product = constructProductForSave(req);
			saveProduct(product);
		} else if (action.equals("Add")) {
			product = constructProductForAdd(req);
			addProduct(product);
		}

		resp.sendRedirect(req.getContextPath() + "/dashboard");
	}

	private Product constructProductForAdd(HttpServletRequest req) throws ServletException, IOException {
		String name = req.getParameter("name");
		String country = req.getParameter("country");
		float price = Float.parseFloat(req.getParameter("price"));
		int amount = Integer.parseInt(req.getParameter("amount"));

		Date deliveryDate = null;
		try {
			String deliveryDateString = req.getParameter("deliveryDate");
			if (!deliveryDateString.isEmpty()) {
				deliveryDate = formatDateService.formatDate(deliveryDateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		byte[] image = null;
		Part filePart = req.getPart("image");
		if (!filePart.getSubmittedFileName().isEmpty()) {
			image = filePartToBytes(filePart);
		}

		return new Product(name, country, price, amount, deliveryDate, image);
	}

	private Product constructProductForSave(HttpServletRequest req) throws ServletException, IOException {
		Product product = constructProductForAdd(req);
		int id = Integer.parseInt(req.getParameter("id"));
		product.setId(id);
		return product;
	}

	private void saveProduct(Product product) {
		try {
			productService.saveProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addProduct(Product product) {
		try {
			productService.addProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] filePartToBytes(Part filePart) throws IOException {
		InputStream inputStream = filePart.getInputStream();
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		baos.close();
		return bytes;
	}

}
