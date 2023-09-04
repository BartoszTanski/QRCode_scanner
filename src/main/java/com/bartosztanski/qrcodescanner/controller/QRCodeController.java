package com.bartosztanski.qrcodescanner.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bartosztanski.qrcodescanner.service.QRCodeService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

@RestController
@RequestMapping(value="/api/v1/qrc")
public class QRCodeController {
	
	private final QRCodeService qrCodeService;
	
	public QRCodeController(QRCodeService qrCodeService) {
		this.qrCodeService = qrCodeService;
	}
	
	
	@GetMapping(value="/generate", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> generateQRC(@RequestParam("data") String data ) throws WriterException, IOException {
		ByteArrayOutputStream os = qrCodeService.generate(data, "UTF-8", 200, 200);
		return new ResponseEntity<>(os.toByteArray(), HttpStatus.OK);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping(value="/scan")
	public ResponseEntity<String> scanQRC(@RequestBody MultipartFile file) throws NotFoundException, IOException {
		BufferedImage image = ImageIO.read(file.getInputStream());
		String s = qrCodeService.scan(image);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
}
