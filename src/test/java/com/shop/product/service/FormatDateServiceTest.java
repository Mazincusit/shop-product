package com.shop.product.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.shop.product.service.FormatDateService;
import com.shop.product.service.impl.FormatDateServiceImpl;

public class FormatDateServiceTest {
	
	@Test
	public void formatDateTest() {
		String date = "2017-02-15";
		
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date expectedDate = null;
		try {
			expectedDate = formatter.parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		FormatDateService formatDateService = new FormatDateServiceImpl();
		Date actualDate = null;
		try {
			actualDate = formatDateService.formatDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Assert.assertTrue(actualDate.compareTo(expectedDate) == 0);
	}

}
