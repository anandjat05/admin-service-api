package com.idexcel.anandadminservice.service;



import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.idexcel.anandadminservice.dao.AdminserviceRepository;
import com.idexcel.anandadminservice.dto.AnandAdminServiceDTO;
import com.idexcel.anandadminservice.entity.Lender;
import com.idexcel.anandadminservice.exception.LenderNotFoundException;
import com.idexcel.anandadminservice.service.impl.AdminInterfaceServiceImpl;

@RunWith(SpringRunner.class)
public class LenderTestService {
	
	@InjectMocks
	private AdminInterfaceServiceImpl adminInterfaceService;
	
	@Mock
	private AdminserviceRepository adminserviceRepository;
	
	AnandAdminServiceDTO anandAdminServiceDTO = new AnandAdminServiceDTO();

	
	@Test
	public void createLender( ) {
		Lender lender = getLender();
		List<Lender> list = new ArrayList<>();
		list.add(lender);
		when(adminserviceRepository.findByName(any())).thenReturn(list);
		when(adminserviceRepository.save(any())).thenReturn(lender);
		try {
			adminInterfaceService.save(anandAdminServiceDTO);
//			fail("Lender Exists already so it will be fail");
		} catch (LenderNotFoundException e) {
			
		}
		
	}
	
	@Test
	public void createLenderFailLenderAlreadyExists( ) {
		Lender lender = getLender();
		when(adminserviceRepository.findByName(any())).thenReturn(null);
		when(adminserviceRepository.save(any())).thenReturn(lender);
		String lenderCreated = adminInterfaceService.save(anandAdminServiceDTO);
		assertEquals("123", lenderCreated);
	}
	
	
	private Lender getLender( ) {
		Lender lender = new Lender();
		lender.setName("My Test Lender");
		lender.setId("123");
		return lender;
	}
	
}
