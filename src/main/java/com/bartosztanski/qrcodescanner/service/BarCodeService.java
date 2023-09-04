package com.bartosztanski.qrcodescanner.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.bartosztanski.qrcodescanner.model.ImageFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

public interface BarCodeService {
	public ByteArrayOutputStream generate(String data,
            String charset, int height, int width) throws WriterException, IOException;
	
	public void generateToPath(String data, ImageFormat format, String path,
            String charset, int height, int width) throws WriterException, IOException;
	
	public String scan(BufferedImage image) throws NotFoundException;
}
