package com.bartosztanski.qrcodescanner.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

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
	 * @param data to be encoded as QRCode
	 * @param charset of data
	 * @param height of image
	 * @param width of image
	 * @return BufferedImage QRCode as Image
	 * @throws IOException 
	 */
	
	public ByteArrayOutputStream generate(String data,
	            String charset, int height, int width) throws WriterException, IOException  {
				
				BitMatrix matrix = new MultiFormatWriter()
						.encode(new String(data.getBytes(charset), charset),
						BarcodeFormat.QR_CODE, width, height);
				
				//Returning Image
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(matrix, "JPG", os);
				return os;
	}
	
	public void generateToPath(String data, ImageFormat format, String path,
            String charset, int height, int width) throws WriterException, IOException {

			BitMatrix matrix = new MultiFormatWriter()
					.encode(new String(data.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, width, height);
			//Writing Image To Path
			MatrixToImageWriter.writeToPath(matrix, format.name(), new File(path).toPath());		
}

	@Override
	public String scan(BufferedImage image) throws NotFoundException {
     BinaryBitmap binaryBitmap
         = new BinaryBitmap(new HybridBinarizer(
             new BufferedImageLuminanceSource(image)));

     Result result
         = new MultiFormatReader().decode(binaryBitmap);

     return result.getText();
   
	}
}