package com.idexcel.anandadminservice.dto;

import com.idexcel.anandadminservice.entity.Address;

import lombok.ToString;

@ToString
public class AnandAdminServiceGetAllDTO {
	
	private String id;
	private String name;
	private Address address;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	
	public AnandAdminServiceGetAllDTO() {
		
	}
	
	public AnandAdminServiceGetAllDTO(String id, String name, Address address, String createdBy, String createdDate,
			String updatedBy, String updatedDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
