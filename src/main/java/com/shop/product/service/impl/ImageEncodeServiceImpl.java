package com.shop.product.service.impl;

import org.apache.commons.codec.binary.Base64;

import com.shop.product.service.ImageEncodeService;

public class ImageEncodeServiceImpl implements ImageEncodeService {

	@Override
	public String encodeImage(byte[] image) {
		if (image == null)
			return null;
		return new String(Base64.encodeBase64(image));
	}

}
