package com.bartosztanski.qrcodescanner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

@SpringBootApplication
public class QrcodeScannerApplication {

	public static void main(String[] args) throws NotFoundException, WriterException, IOException {
		SpringApplication.run(QrcodeScannerApplication.class, args);
		
	}
}
