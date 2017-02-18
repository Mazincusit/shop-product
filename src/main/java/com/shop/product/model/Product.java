package com.shop.product.model;

import java.util.Date;

public class Product {
	private int id;
	private String name;
	private String country;
	private float price;
	private int amount;
	private Date deliveryDate;
	private byte[] image;

	public Product() {
	}

	public Product(String name, String country, float price, int amount, Date deliveryDate, byte[] image) {
		this.name = name;
		this.country = country;
		this.price = price;
		this.amount = amount;
		this.deliveryDate = deliveryDate;
		this.image = image;
	}

	public Product(int id, String name, String country, float price, int amount, Date deliveryDate, byte[] image) {
		this(name, country, price, amount, deliveryDate, image);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
