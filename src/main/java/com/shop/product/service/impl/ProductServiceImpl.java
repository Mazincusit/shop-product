package com.shop.product.service.impl;

import java.util.List;

import com.shop.product.dao.ProductDAO;
import com.shop.product.dao.impl.ProductDAOImpl;
import com.shop.product.model.Product;
import com.shop.product.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDAO dao;

	public ProductServiceImpl() {
		this.dao = new ProductDAOImpl();
	}

	@Override
	public long getProductsSize() throws Exception {
		return dao.getProducts("", 0L, 0).size();
	}

	@Override
	public long getProductsSize(String query) throws Exception {
		return dao.getProducts(query, 0L, 0).size();
	}

	@Override
	public List<Product> getProducts(String query) throws Exception {
		return dao.getProducts(query, 0L, 0);
	}

	@Override
	public List<Product> getProducts(String query, long offset, int limit) throws Exception {
		return dao.getProducts(query, offset, limit);
	}

	@Override
	public Product getProduct(long id) throws Exception {
		return dao.getProduct(id);
	}

	public void deleteProducts(String[] ids) throws Exception {
		dao.deleteProducts(ids);
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		dao.saveProduct(product);
	}

	@Override
	public void addProduct(Product product) throws Exception {
		dao.addProduct(product);
	}

}
