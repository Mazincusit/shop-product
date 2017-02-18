package com.shop.product.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shop.product.service.FormatDateService;

public class FormatDateServiceImpl implements FormatDateService {
	private static final String pattern = "yyyy-mm-dd";

	@Override
	public Date formatDate(String stringDate) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(stringDate);
	}

}
