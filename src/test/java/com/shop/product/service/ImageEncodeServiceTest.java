package com.shop.product.service;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import com.shop.product.service.ImageEncodeService;
import com.shop.product.service.impl.ImageEncodeServiceImpl;

public class ImageEncodeServiceTest {

	@Test
	public void encodeImageTest() {
		ImageEncodeService imageEncodeService = new ImageEncodeServiceImpl();
		
		byte[] image = new byte[10];
		new Random().nextBytes(image);
		
		String expectedEncodedImage = new String(Base64.encodeBase64(image));
		String actualEncodedImage = imageEncodeService.encodeImage(image);
		
		Assert.assertTrue(actualEncodedImage.equals(expectedEncodedImage));
	}
	
}
