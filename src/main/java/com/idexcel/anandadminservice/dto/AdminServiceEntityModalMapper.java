package com.idexcel.anandadminservice.dto;

import org.modelmapper.ModelMapper;

import com.idexcel.anandadminservice.entity.Lender;

public class AdminServiceEntityModalMapper {
	
	ModelMapper modelMapper = new ModelMapper();
	
	public AnandAdminServiceDTO convertToDTO(Lender theLender) {
		//converting POJO Lender class to DTO class
		AnandAdminServiceDTO anandAdminServiceDTO=modelMapper.map(theLender, AnandAdminServiceDTO.class);
		return anandAdminServiceDTO;
	}
	
	public Lender convertToEntity(AnandAdminServiceDTO theAnandAdminServiceDTO) {
		//convert DTO class to POJO class
		Lender theLender = modelMapper.map(theAnandAdminServiceDTO, Lender.class);
		return theLender;
	}
	
}
