package com.shop.product.testenv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestDataFiller {

	private static final String IMAGES_PATH = "external/images/";

	private static final String URL = "jdbc:mysql://localhost:3306/db_shop_product";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
	
		List<Product> products = initializeProducts();
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			clearDB(conn);
			resetProductAutoIncrement(conn);
			for (Product product : products) {
				try {
					String sql = "INSERT INTO product (name, country, price, amount, delivery_date, image) " +
							"VALUES (?, ?, ?, ?, ?, ?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, product.getName());
					stmt.setString(2, product.getCountry());
					stmt.setFloat(3, product.getPrice());
					stmt.setInt(4, product.getAmount());
					stmt.setDate(5, getProductDeliveryDate(product));
					stmt.setBytes(6, product.getImage());
					stmt.executeUpdate();
	
					System.out.println("Successfully inserted product into the DB: " + product);
				} catch (SQLException exc) {
					System.out.println("Problem has been detected: " + exc);
				}
			}
			System.out.println("Database is now filled");
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static List<Product> initializeProducts() throws IOException {
		List<Product> products = new ArrayList<Product>();
		
		// Hammer
		String name = "Hammer";
		String country = "China";
		float price = 10.9f;
		int amount = 100;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		Date deliveryDate = calendar.getTime();
		
		Product product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);

		System.out.println("Successfully constructed product: " + product);
		
		products.add(product);
		
		// Screwdriver
		name = "Screwdriver";
		country = "China";
		price = 8.5f;
		amount = 150;

		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);

		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Nail
		name = "Nail";
		country = "India";
		price = 0.1f;
		amount = 3000;

		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 7);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Ruler
		name = "Ruler";
		country = "China";
		price = 2.0f;
		amount = 200;

		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 3);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Scrap
		name = "Scrap";
		country = "USA";
		price = 18.0f;
		amount = 80;

		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 5);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Pliers
		name = "Pliers";
		country = "Norway";
		price = 12.5f;
		amount = 120;

		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 17);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Rubber
		name = "Rubber";
		country = "China";
		price = 7.25f;
		amount = 100;

		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Hacksaw
		name = "Hacksaw";
		country = "China";
		price = 9.75f;
		amount = 40;

		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Hacksaw
		name = "Wrench";
		country = "Ukraine";
		price = 4.5f;
		amount = 400;

		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 5);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		// Vise
		name = "Vise";
		country = "Poland";
		price = 23f;
		amount = 100;

		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, Calendar.OCTOBER);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		deliveryDate = calendar.getTime();
		
		product = new Product(name, country, price, amount, deliveryDate);
		product = setProductImage(product);
		
		System.out.println("Successfully constructed product: " + product);

		products.add(product);
		
		return products;
	}

	private static void clearDB(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DELETE FROM product");
		System.out.println("DB is now creared");
	}

	private static void resetProductAutoIncrement(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("ALTER TABLE product AUTO_INCREMENT = 1");
		System.out.println("Product's auto-increment is now reset");
	}
	
	private static byte[] fileToBypes(File file) throws IOException {
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytes);
		fis.close();
		return bytes;
	}
	
	private static Product setProductImage(Product product) throws IOException {
		File file = new File(IMAGES_PATH + product.getName() + ".jpg");
		byte[] image = fileToBypes(file);
		product.setImage(image);
		return product;
	}

	private static java.sql.Date getProductDeliveryDate(Product product) {
		Date deliveryDate = product.getDeliveryDate();
		return deliveryDate == null ? null : new java.sql.Date(deliveryDate.getTime());
	}

	private static class Product {
		private String name;
		private String country;
		private float price;
		private int amount;
		private Date deliveryDate;
		private byte[] image;
		
		public Product(String name, String country, float price, int amount, Date deliveryDate) {
			this.name = name;
			this.country = country;
			this.price = price;
			this.amount = amount;
			this.deliveryDate = deliveryDate;
		}

		public String getName() {
			return name;
		}

		public String getCountry() {
			return country;
		}

		public float getPrice() {
			return price;
		}

		public int getAmount() {
			return amount;
		}

		public Date getDeliveryDate() {
			return deliveryDate;
		}

		public byte[] getImage() {
			return image;
		}

		public void setImage(byte[] image) {
			this.image = image;
		}
		
		@Override
		public String toString() {
			return "Product [name=" + name + ", country=" + country + ", price=" + price + ", amount=" + amount
					+ ", deliveryDate=" + deliveryDate + "]";
		}
	}
}
