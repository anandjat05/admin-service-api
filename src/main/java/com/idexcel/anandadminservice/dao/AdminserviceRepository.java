package com.idexcel.anandadminservice.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.idexcel.anandadminservice.entity.Lender;

public interface AdminserviceRepository extends MongoRepository<Lender, String> {
	//it will create crud operation automatically
	

	@Query("{name : ?0}")
	public List<Lender> findByName(String lenderName);
	
	@Query("{id : ?0}")
	public Lender findByLenderId(String lenderId);
	
}
