package com.bartosztanski.qrcodescanner.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.bartosztanski.qrcodescanner.error.ImageScanFailureException;
import com.bartosztanski.qrcodescanner.model.ImageFormat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

@Service
public class QRCodeService implements BarCodeService{
	
	/**
	 * generates QRC for given String and returns as {@code BufferedImage}
	 * @param data to be encoded as QRCode
	 * @param charset of data
	 * @param height of image
	 * @param width of image
	 * @return BufferedImage QRCode as Image
	 * @throws WriterException 
	 * @throws IOException,WriterException 
	 */
	
	public ByteArrayOutputStream generate(String data,
	            String charset, int height, int width) throws WriterException, IOException   {
				
				BitMatrix matrix = new MultiFormatWriter()
						.encode(new String(data.getBytes(charset), charset),
						BarcodeFormat.QR_CODE, width, height);
				
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(matrix, "JPG", os);
				return os;
	}
	
	/**
	 * generates QRCode JPEG image and saves to file
	 * @param data to be encoded as QRCode
	 * @param charset of data
	 * @param path to which QRCode image will be saved
	 * @param height of image
	 * @param width of image
	 * @throws IOException,WriterException 
	 */
	
	public void generateToPath(String data, ImageFormat format, String path,
            String charset, int height, int width) throws WriterException, IOException {

			BitMatrix matrix = new MultiFormatWriter()
					.encode(new String(data.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, width, height);
			//Writing Image To Path
			MatrixToImageWriter.writeToPath(matrix, format.name(), new File(path).toPath());		
}
	
	/**
	 * scans image of QRC
	 * @param image QRCode as Image
	 * @return String data which QRCode contains
	 * @throws ImageScanFailureException 
	 */

	@Override
	public String scan(BufferedImage image) throws ImageScanFailureException {
     BinaryBitmap binaryBitmap
         = new BinaryBitmap(new HybridBinarizer(
             new BufferedImageLuminanceSource(image)));
     
     try{
    	 Result result = new MultiFormatReader().decode(binaryBitmap);
    	 return result.getText();
     }
     catch (NotFoundException e) {
		throw new ImageScanFailureException("Image scan failed, try with diffrent image.");
     }
   
	}
}