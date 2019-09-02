package com.idexcel.anandadminservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idexcel.anandadminservice.entity.Lender;

public interface AdminserviceRepository extends MongoRepository<Lender, String> {
	//it will create crud operation automatically
	
}
