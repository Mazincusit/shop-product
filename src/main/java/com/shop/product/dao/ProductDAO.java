package com.shop.product.dao;

import java.util.List;

import com.shop.product.model.Product;

public interface ProductDAO {
	
	List<Product> getProducts(String query, long offset, int limit) throws Exception;
	
	Product getProduct(long id) throws Exception;
	
	void deleteProducts(String[] ids) throws Exception;
	
	void saveProduct(Product product) throws Exception;
	
	void addProduct(Product product) throws Exception;
	
}
