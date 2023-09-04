package com.bartosztanski.qrcodescanner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.bartosztanski.qrcodescanner.service.QRCodeService;

@WebMvcTest
public class QRCodeControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private QRCodeService service;
	
	@Test
	public void givenTextData_whenGetQRCode_thenReturnJsonArray()
	  throws Exception {

	    mvc.perform(get("/api/v1/qrc/generate")
	      .param("data", "some text")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	
	@Test
	public void givenImage_whenScanImage_thenReturnJsonData()
	  throws Exception {
		MultipartFile file = new MockMultipartFile(
				"test.jpg", new FileInputStream(new File(".//src//test//resources//test.jpg")));
	    mvc.perform(post("/api/v1/qrc/scan")
	      .flashAttr("file",file)
	      .contentType(MediaType.MULTIPART_FORM_DATA))
	      .andExpect(status().isOk());
	}
}
