package com.bartosztanski.qrcodescanner.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

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

import com.bartosztanski.qrcodescanner.error.ArgumentNotValidException;
import com.bartosztanski.qrcodescanner.error.ImageScanFailureException;
import com.bartosztanski.qrcodescanner.service.QRCodeService;
import com.google.zxing.WriterException;

@RestController
@RequestMapping(value="/api/v1/qrc")
public class QRCodeController {
	
	private final QRCodeService qrCodeService;
	
	public QRCodeController(QRCodeService qrCodeService) {
		this.qrCodeService = qrCodeService;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping(value="/generate", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> generateQRC(@RequestParam("data") String data, 
			@RequestParam("charset") Optional<String> charset, 
			@RequestParam("size") Optional<Integer> size ) 
					throws WriterException, IOException, ArgumentNotValidException {
		
		int imageSize = size.orElse(200);
		if (imageSize<1) throw new ArgumentNotValidException(
				String.format("Requested dimensions are too small: %s x %s",imageSize,imageSize));
		if (data.isEmpty()) throw new ArgumentNotValidException("Data is empty");
		
		ByteArrayOutputStream os = 
				qrCodeService.generate(data, charset.orElse("UTF-8"),imageSize, imageSize);
		return new ResponseEntity<>(os.toByteArray(), HttpStatus.OK);
	}
	
	@PostMapping(value="/scan")
	public ResponseEntity<String> scanQRC(@RequestBody MultipartFile file) throws IOException, ImageScanFailureException {
		BufferedImage image = ImageIO.read(file.getInputStream());
		String data = qrCodeService.scan(image);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
