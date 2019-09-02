package com.idexcel.anandadminservice.service.impl;

import java.util.List;

import com.idexcel.anandadminservice.dto.AnandAdminServiceDTO;
import com.idexcel.anandadminservice.dto.LenderPatchDTO;
import com.idexcel.anandadminservice.entity.Lender;

public interface AdminInterfaceService {
	
	public List<Lender> findAll();
	public Lender findById(String theId);
	public String save(AnandAdminServiceDTO theAnandAdminServiceDTO);
	public void update(Lender theLender, String theId);
	public void deleteById(String theId);
	public void updateStatus(LenderPatchDTO theLendersPatchDto, String theId);
	public boolean lenderCheck(String theId);
	
}
