package com.shop.product.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.shop.product.dao.ProductDAO;
import com.shop.product.model.Product;

public class ProductDAOImpl implements ProductDAO {
	private static final String JNDI_DATA_SOURCE_NAME = "java:comp/env/jdbc/shop_product";

	@Override
	public List<Product> getProducts(String query, long offset, int limit) throws Exception {
		List<Product> products = new ArrayList<Product>();
		
		Context cxt = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup(JNDI_DATA_SOURCE_NAME);
			conn = ds.getConnection();
			String sql = "SELECT * FROM product";

			if (!query.isEmpty()) {
				sql += " WHERE name LIKE ?";
			}
			if (limit != 0) {
				sql += " LIMIT ?, ?";
			}

			stmt = conn.prepareStatement(sql);
			if (!query.isEmpty()) {
				stmt.setString(1, '%' + query + '%');
				if (limit != 0) {
					stmt.setLong(2, offset);
					stmt.setInt(3, limit);
				}
			} else if (limit != 0) {
				stmt.setLong(1, offset);
				stmt.setInt(2, limit);
			}
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
				Date deliveryDate = rs.getDate("delivery_date");
				byte[] image = rs.getBytes("image");
				
				Product product = new Product(id, name, country, price, amount, deliveryDate, image);
				
				products.add(product);
			}
		} finally {
			close(conn, stmt, rs);
		}
		
		return products;
	}

	@Override
	public void deleteProducts(String[] ids) throws Exception {
		if (ids == null) return;
		
		Context cxt = null;
		DataSource ds = null;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup(JNDI_DATA_SOURCE_NAME);
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM product WHERE id IN (";
			for (String id : ids)
				sql += id + ',';
			sql = sql.substring(0, sql.length() - 1);
			sql += ')';
			
			stmt.executeUpdate(sql);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public Product getProduct(long id) throws Exception {
		Product product = null;

		Context cxt = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup(JNDI_DATA_SOURCE_NAME);
			conn = ds.getConnection();
			String sql = "SELECT * FROM product WHERE id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int productId = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
				Date deliveryDate = rs.getDate("delivery_date");
				byte[] image = rs.getBytes("image");
				
				product = new Product(productId, name, country, price, amount, deliveryDate, image);
			}
		} finally {
			close(conn, stmt, rs);
		}

		return product;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		Context cxt = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup(JNDI_DATA_SOURCE_NAME);
			conn = ds.getConnection();
			byte[] image = product.getImage();
			
			String sql = "UPDATE product SET name=?, country=?, price=?, amount=?, delivery_date=?";
			if (image != null) {
				sql += ", image=?";
			}
			sql += " WHERE id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCountry());
			stmt.setFloat(3, product.getPrice());
			stmt.setInt(4, product.getAmount());
			stmt.setDate(5, getProductDeliveryDate(product));
			
			if (image != null) {
				stmt.setBytes(6, product.getImage());
			}
			
			stmt.setInt(image == null ? 6 : 7, product.getId());
			
			stmt.execute();
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void addProduct(Product product) throws Exception {
		Context cxt = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup(JNDI_DATA_SOURCE_NAME);
			conn = ds.getConnection();
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
		} finally {
			close(conn, stmt, null);
		}
	}
	
	private java.sql.Date getProductDeliveryDate(Product product) {
		Date deliveryDate = product.getDeliveryDate();
		return deliveryDate == null ? null : new java.sql.Date(deliveryDate.getTime());
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null) conn.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
