package com.idexcel.anandadminservices.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexcel.anandadminservice.AnandAdminServiceApplication;
import com.idexcel.anandadminservice.dto.AnandAdminServiceDTO;
import com.idexcel.anandadminservice.service.impl.AdminInterfaceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AnandAdminServiceApplication.class)
@WebAppConfiguration
public class LenderTestController {
	
	
	protected MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@MockBean 
	AdminInterfaceService adminInterfaceService;
	
	@Test
	public void lenderCreateTest() throws Exception {
		
		when(adminInterfaceService.save(any(AnandAdminServiceDTO.class))).thenReturn("Return Any Id");
		mockMvc.perform( MockMvcRequestBuilders
				.post("/lenders")
				.content(convertObjectToJson(getLender()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void test() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/lenders/test")).andExpect(status().isOk());
	}
	
	//Usable method 
	private static String convertObjectToJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static AnandAdminServiceDTO getLender() {
		AnandAdminServiceDTO theAnandAdminServiceDTO = new AnandAdminServiceDTO();
		theAnandAdminServiceDTO.setName("My Tesing Lender");
		return theAnandAdminServiceDTO;
	}
	
}
