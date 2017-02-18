package com.shop.product.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.shop.product.model.Product;
import com.shop.product.service.ProductService;
import com.shop.product.service.impl.ProductServiceImpl;

public class ProductServiceTest {
	
	private ProductService productService;
	
	public ProductServiceTest() {
		productService = new ProductServiceImpl();
	}
	
	@BeforeClass
	public static void setUp() throws NamingException {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost:3306/db_shop_product");
        ds.setUser("root");
        ds.setPassword("password");
		
        Context ic = new InitialContext();
        ic.createSubcontext("java:");
        ic.createSubcontext("java:comp");
        ic.createSubcontext("java:comp/env");
        ic.createSubcontext("java:comp/env/jdbc");
        ic.bind("java:comp/env/jdbc/shop_product", ds);
	}

	@Before @After
	public void removeProductsBeforeAndAfterEachTest() {
		try {
			if (productService.getProductsSize() > 0) {
				List<Product> products = productService.getProducts("");
				String idsString = "";
				for (Product product : products) {
					idsString = idsString.concat(product.getId() + ",");
				}
				if (idsString.length() > 0) {
					idsString = idsString.substring(0, idsString.length() - 1);
					String[] ids = idsString.split(",");
					productService.deleteProducts(ids);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getProductsSizeTest() {
		try {
			Product product = new Product();
			product.setName("Test");
			product.setCountry("Test");
			productService.addProduct(product);

			product = new Product();
			product.setName("Test 2");
			product.setCountry("Test 2");
			productService.addProduct(product);

			Assert.assertTrue(productService.getProductsSize() == 2);
			Assert.assertTrue(productService.getProductsSize("Test 2") == 1);
			Assert.assertTrue(productService.getProductsSize("Test 3") == 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getProductsTest() {
		try {
			Product product = new Product();
			product.setName("Test");
			product.setCountry("Test");
			productService.addProduct(product);

			product = new Product();
			product.setName("Test 2");
			product.setCountry("Test 2");
			productService.addProduct(product);

			List<Product> products = productService.getProducts("", 0L, 1);
			Assert.assertTrue(products.size() == 1);

			products = productService.getProducts("", 1L, 2);
			Assert.assertTrue(products.size() == 1);

			products = productService.getProducts("");
			Assert.assertTrue(products.size() == 2);

			products = productService.getProducts("Test");
			Assert.assertTrue(products.size() == 2);

			products = productService.getProducts("Test 2");
			Assert.assertTrue(products.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteProductsTest() {
		try {
			Product product = new Product();
			product.setName("Test");
			product.setCountry("Test");
			productService.addProduct(product);
			
			List<Product> products = productService.getProducts("");
			Product tempProduct = products.get(0);
			long tempId = tempProduct.getId();
			
			productService.deleteProducts(new String[] { String.valueOf(tempId) });

			Assert.assertTrue(productService.getProductsSize() == 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getProductTest() {
		try {
			Product product = new Product();
			product.setName("Test");
			product.setCountry("Test");
			productService.addProduct(product);
			
			List<Product> products = productService.getProducts("");
			product = products.get(0);

			Assert.assertTrue(product.getName().equals("Test"));
			Assert.assertTrue(product.getCountry().equals("Test"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void saveProductTest() {
		try {
			Product product = new Product();
			product.setName("Test");
			product.setCountry("Test");
			productService.addProduct(product);
			
			List<Product> products = productService.getProducts("");
			product = products.get(0);
			product.setCountry("Test 2");
			productService.saveProduct(product);
			
			products = productService.getProducts("");
			product = products.get(0);
			
			Assert.assertTrue(product.getCountry().equals("Test 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addProductTest() {
		try {
			Product product = new Product();
			product.setName("Test");
			product.setCountry("Test");
			productService.addProduct(product);
			
			List<Product> products = productService.getProducts("");
			product = products.get(0);

			Assert.assertTrue(product.getName().equals("Test"));
			Assert.assertTrue(product.getCountry().equals("Test"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
