package com.idexcel.anandadminservice.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.idexcel.anandadminservice.dao.AdminserviceRepository;
import com.idexcel.anandadminservice.dto.AdminServiceEntityModalMapper;
import com.idexcel.anandadminservice.dto.AnandAdminServiceDTO;
import com.idexcel.anandadminservice.dto.LenderPatchDTO;
import com.idexcel.anandadminservice.entity.Lender;
import com.idexcel.anandadminservice.entity.Post;
import com.idexcel.anandadminservice.exception.LenderNotFoundException;


@Service
public class AdminInterfaceServiceImpl implements AdminInterfaceService {

	private AdminserviceRepository adminserviceRepository;
	AdminServiceEntityModalMapper theAdminServiceEntityModalMapper = new AdminServiceEntityModalMapper();
	
	@Autowired
	public AdminInterfaceServiceImpl(AdminserviceRepository adminserviceRepository) {
		this.adminserviceRepository = adminserviceRepository;
	}

	@Override
	public List<Lender> findAll() {
		// TODO Auto-generated method stub
		List<Lender> allLenders = this.adminserviceRepository.findAll(Sort.by(Direction.DESC, "createdDate"));
		return allLenders;
	}

	@Override
	public Lender findById(String theId) {
		// TODO Auto-generated method stub
		//It will check for the null
		Optional<Lender> result = adminserviceRepository.findById(theId);
		Lender theLender =null;
		if(result.isPresent()) {
			theLender = result.get();
		} else {
			throw new LenderNotFoundException("Did not find the Id: "+theId);
		}
		return theLender;
	}

	@Override
	public String save(AnandAdminServiceDTO theAnandAdminServiceDTO) {
		
		
//		if(theAnandAdminServiceDTO.getName()==null) {
//			
			Lender theLender = theAdminServiceEntityModalMapper.convertToEntity(theAnandAdminServiceDTO);
			theLender.setStatus("Active");
			theLender.setCreatedBy("Anand Jat");
			theLender.setCreatedDate(Calendar.getInstance().getTime());
			theLender.setUpdatedBy("Anand");
			theLender.setUpdatedDate(Calendar.getInstance().getTime());
			adminserviceRepository.insert(theLender);
			return theLender.getId();
			
//		}else {
//			//throw an exception
//			throw new LenderNotFoundException("Did not find the Lender");
//			
//		}
		
	}

	@Override
	public void deleteById(String theId) {
		// TODO Auto-generated method stub
		this.adminserviceRepository.deleteById(theId);
		
	}

	@Override
	public void update(Lender theLender, String theId) {
		// TODO Auto-generated method stub
		this.adminserviceRepository.save(theLender);
	}

	@Override
	public void updateStatus(LenderPatchDTO theLendersPatchDto, String theId) {
		// TODO Auto-generated method stub
		if(adminserviceRepository.existsById(theId)) {
			Lender theLender = this.adminserviceRepository.findById(theId).orElse(null);
			theLender.setStatus(theLendersPatchDto.getStatus());
			this.adminserviceRepository.save(theLender);
			
		}else {
			throw new LenderNotFoundException("Lender not found with id: "+theId);
		}
	}

	@Override
	public boolean lenderCheck(String theId) {
		
		if(this.adminserviceRepository.existsById(theId)) {
			
			return true;
		}else {
			throw new LenderNotFoundException("The LenderNot found with the id: "+theId);
		}
		
	}

	@Override
	public ResponseEntity<List> getUserPostExternal() {
		
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://jsonplaceholder.typicode.com/posts";
		ResponseEntity<List> userPosts = restTemplate.getForEntity(resourceUrl, List.class);
		return userPosts;
	}

	@Override
	public ResponseEntity<Post> getPostByIdExternal(int id) {
		
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://jsonplaceholder.typicode.com/posts/";
		ResponseEntity<Post> post = restTemplate.getForEntity(resourceUrl+id, Post.class);
		return post;
		
		
	}
	
	

}
