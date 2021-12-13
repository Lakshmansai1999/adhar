package com.example.demoadhar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoadhar.dto.AdharUsersDto;
import com.example.demoadhar.entity.AdharUsers;
import com.example.demoadhar.service.AdharUsersService;

@RestController
@RequestMapping("/AdharUsers")
public class AdharUsersController {
	@Autowired
	private AdharUsersService adharusersService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
    public List<AdharUsers>  getAllAdharUsers() {
       return this.adharusersService.getAllAdharUsers(); 
    }
	@PostMapping
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<AdharUsers> createAdharUsers(@RequestBody AdharUsersDto adharusersDto)throws Exception{
		AdharUsers adharusers = this.adharusersService.createAdharUsers(adharusersDto);
		return new ResponseEntity<>(adharusers,HttpStatus.CREATED);
	}
	@GetMapping(value="/{auid}")
	public ResponseEntity<AdharUsers> getUserByAuid(@PathVariable int auid) {
		return ResponseEntity.ok().body(this.adharusersService.findByAuid(auid));
	} 
	@DeleteMapping(value="/{auid}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public HttpStatus delete(@PathVariable int auid) {
			System.out.println("delete");
			this.adharusersService.deleteByAuid(auid);
			return HttpStatus.OK;
	        
	    }
	@PutMapping(value="/{auid}")
	public ResponseEntity<AdharUsers> update(@RequestBody AdharUsersDto adharusers,@PathVariable int auid) {
	adharusers.setAuid(auid);
		return ResponseEntity.ok().body(adharusersService.update(adharusers));
		
		}

}
