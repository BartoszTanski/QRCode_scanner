package com.bartosztanski.qrcodescanner;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Name;

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

public class QRCodeServiceTest {
	
	String data = "some text";
	String path = ".//src//test//resources//test.jpg";
	
	@Test
	void shouldGenerateByteArrayOutputStream() throws WriterException, IOException {
		//String data = "some text";
		String charset = "UTF-8";
		int width = 200;
		int height = 200;
		BitMatrix matrix = new MultiFormatWriter()
				.encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "JPG", os);
		assertThat(os.size()).isGreaterThan(0);
		os.close();
	}
	
	@Test
	void shouldGenerateQRCodeImage() throws WriterException, IOException {
		//String data = "test data";
		ImageFormat format = ImageFormat.JPEG;
		//String path = "test.jpg";
        String charset = "UTF-8";
		int height = 200;
		int width = 200; 
		
		BitMatrix matrix = new MultiFormatWriter()
				.encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);
		Long fileCreationTime = System.currentTimeMillis();
		MatrixToImageWriter.writeToPath(matrix, format.name(), new File(path).toPath());
		assertThat(new File(path).lastModified()).isGreaterThan(fileCreationTime);
	}
	@Test
	void shouldReturnDataEncodedInQRCode() throws NotFoundException, IOException {
		//String path = "test.jpg";
		BufferedImage image =  ImageIO.read(new File(path));
	
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
            new BufferedImageLuminanceSource(image)));
    
   	 Result result = new MultiFormatReader().decode(binaryBitmap);
   	 assertThat(result.getText()).isEqualTo(data);
	}
	@Test
	void shouldReturnExactDataEncodedInQRCode() throws NotFoundException, IOException {
		//String path = "test.jpg";
		BufferedImage image =  ImageIO.read(new File(path));
	
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
            new BufferedImageLuminanceSource(image)));
    
   	 Result result = new MultiFormatReader().decode(binaryBitmap);
   	 assertThat(result.getText()).isNotEqualTo("q"+data);
	}
}
