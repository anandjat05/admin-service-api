package com.idexcel.anandadminservice.dto;

import javax.validation.constraints.NotNull;

import com.idexcel.anandadminservice.entity.Address;
import lombok.ToString;


@ToString
public class AnandAdminServiceDTO {
	
	@NotNull
	private String name;
	
	@NotNull
	private Address address;
	
	public AnandAdminServiceDTO() {
		
	}

	public AnandAdminServiceDTO(@NotNull String name, @NotNull Address address) {
		this.name = name;
		this.address = address;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	

	
}
