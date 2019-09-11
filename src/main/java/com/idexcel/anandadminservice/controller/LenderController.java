package com.idexcel.anandadminservice.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.idexcel.anandadminservice.dto.AnandAdminServiceDTO;
import com.idexcel.anandadminservice.dto.LenderPatchDTO;
import com.idexcel.anandadminservice.entity.Lender;
import com.idexcel.anandadminservice.entity.Post;
import com.idexcel.anandadminservice.exception.LenderNotFoundException;
import com.idexcel.anandadminservice.service.impl.AdminInterfaceService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LenderController {
	
	private AdminInterfaceService adminInterfaceService;
	
	@Autowired
	public LenderController(AdminInterfaceService adminInterfaceService) {
		this.adminInterfaceService = adminInterfaceService;
	}
	
	@GetMapping("/lenders")
	public List<Lender> findAll(){
		return adminInterfaceService.findAll();
	}
	//reading the single emp
	@GetMapping("/lenders/{lenderId}")
	public Lender getLender(@PathVariable String lenderId) {
		Lender theLender = adminInterfaceService.findById(lenderId);
		if(theLender ==null) {
			throw new LenderNotFoundException("Lender Id not found: "+lenderId);
		}
		return theLender;
	}
	//add new emp
	@PostMapping("/lenders")
	public ResponseEntity<Object> addLender(@RequestBody AnandAdminServiceDTO theAnandAdminServiceDTO) {
		String id= adminInterfaceService.save(theAnandAdminServiceDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
		
	}
	//update
	@PutMapping("/lenders/{lenderId}")
	public void updateLender(@RequestBody Lender theLender, @PathVariable String lenderId) {
		
		adminInterfaceService.update(theLender, lenderId);
	}
	//delete mapping
	@DeleteMapping("/lenders/{lenderId}")
	public String deleteLender(@PathVariable String lenderId) {
		
		Lender theLender = adminInterfaceService.findById(lenderId);
		 if(theLender ==null){
			 throw new LenderNotFoundException("Lender Not found of Id: "+lenderId);
		 }
		 adminInterfaceService.deleteById(lenderId);
		 return "Now Deleted Employee: "+lenderId;
	}
	//patch mapping
	@PatchMapping("/lenders/{lenderId}/status")
	public void updateStatus(@RequestBody LenderPatchDTO lenderPatchDTO, @PathVariable String lenderId){
		this.adminInterfaceService.updateStatus(lenderPatchDTO, lenderId);
	} 
	
	@RequestMapping(value="/lenders/{lenderId}", method=RequestMethod.HEAD)
	public ResponseEntity <String> headerReturn(@PathVariable String lenderId) {
		
		HttpHeaders theHttpHeaders = new HttpHeaders();
		theHttpHeaders.set("Admin-Service-Header", "Containing all info about the lender");
		ResponseEntity <String> responseEntity = new ResponseEntity<String>("Admin-service header information:", theHttpHeaders, HttpStatus.OK);
		
		if (adminInterfaceService.lenderCheck(lenderId))	{		
			
			return responseEntity;
		}
		return responseEntity;	
		
		 
	}
	//health check mapping
	@GetMapping("/lenders/healthCkeck")
	public String getHealth() {
		return "Anand Admin Service is Running";
	}
	//Calling other microservices to communicate each other
	//We use rest Template
	
	@GetMapping("/post/{id}")
	public ResponseEntity<Post> getAllPost(@PathVariable int id){
		return adminInterfaceService.getPostByIdExternal(id);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List> getAllPost(){
		return adminInterfaceService.getUserPostExternal();
	}
	
	@GetMapping("/health")
	public String healthCheck() {
	
		return "Up and Running, Its awesome!!";
	}
	
}
