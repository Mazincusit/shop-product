package com.shop.product.service;

import java.util.List;

import com.shop.product.model.Product;

public interface ProductService {

	public long getProductsSize() throws Exception;

	public long getProductsSize(String query) throws Exception;

	public List<Product> getProducts(String query) throws Exception;

	public List<Product> getProducts(String query, long offset, int limit) throws Exception;

	Product getProduct(long id) throws Exception;

	void deleteProducts(String[] ids) throws Exception;

	void saveProduct(Product product) throws Exception;

	void addProduct(Product product) throws Exception;

}
