package com.bartosztanski.qrcodescanner.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


//public class QRCodeSevice implements BarCodeService {	 
//	 
//	    // Function to create the QR code
//	public BufferedImage generate(String data, String path,
//            String charset, Map hashMap,
//            int height, int width) throws WriterException, IOException {
//
//		BitMatrix matrix = new MultiFormatWriter()
//				.encode(new String(data.getBytes(charset), charset),
//				BarcodeFormat.QR_CODE, width, height);
//		
//		MatrixToImageWriter.writeToPath(matrix, "jpg",new File(path).toPath());
//		BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);	
//		return image;
//		}
//	    // Driver code
//	    public static BufferedImage startQR()
//	        throws WriterException, IOException,
//	               NotFoundException
//	    {
//	 
//	        // The data that the QR code will contain
//	        String data = "www.geeksforgeeks.org";
//	       
//
//	        // The path where the image will get saved
//	        String path = "demo.png";
//	 
//	        // Encoding charset
//	        String charset = "UTF-8";
//	 
//	        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
//	            = new HashMap<EncodeHintType,
//	                          ErrorCorrectionLevel>();
//	 
//	        hashMap.put(EncodeHintType.ERROR_CORRECTION,
//	                    ErrorCorrectionLevel.L);
//	 
//	        // Create the QR code and save
//	        // in the specified folder
//	        // as a PNG file
//	        BufferedImage image = createQR(data, path, charset, hashMap, 200, 200);
//	        System.out.println("QR Code Generated!!! ");
//	        return image;
//	    }
//		@Override
//		public BufferedImage generate() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		@Override
//		public String scan() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	
//}
