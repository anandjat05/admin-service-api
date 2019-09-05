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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexcel.anandadminservice.AnandAdminServiceApplication;
import com.idexcel.anandadminservice.dto.AnandAdminServiceDTO;
import com.idexcel.anandadminservice.entity.Address;
import com.idexcel.anandadminservice.entity.Lender;
import com.idexcel.anandadminservice.exception.LenderNotFoundException;
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
	public void postAdminTest() throws Exception {
		
		AnandAdminServiceDTO theAnandAdminServiceDTO = new AnandAdminServiceDTO("Testing Name", new Address("Laurel Ave", "Hayward", "CA", "94541", "USA"));
		when(adminInterfaceService.save(theAnandAdminServiceDTO)).thenReturn("123");	
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	
	}
	@Test
	public void patchAdminById() throws Exception{
		Lender lender = new Lender("123", "Testing Name", new Address("wordsworth ct", "herndon", "VA", "20171", "USA"), 
				"Anand Jat", null, "Anand", null, "Active");
		adminInterfaceService.update(lender, "123456");
		RequestBuilder request = MockMvcRequestBuilders.patch("/api/Lenders/12345678/status");
				
		mockMvc.perform(request)
			   .andExpect(status().isNotFound())
			   .andReturn();

		
	}
	
	
	
	

	
}
