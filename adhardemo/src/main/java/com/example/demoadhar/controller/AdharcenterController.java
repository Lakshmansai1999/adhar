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

import com.example.demoadhar.dto.AdharcenterDto;
import com.example.demoadhar.entity.Adharcenter;
import com.example.demoadhar.service.AdharcenterService;

@RestController
@RequestMapping("/Adharcenter")
public class AdharcenterController {
	@Autowired
	private AdharcenterService adharcenterService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
    public List<Adharcenter>  getAllAdharcenters() {
       return this.adharcenterService.getAllAdharcenters(); 
    }
	@PostMapping
	@ResponseStatus(value=HttpStatus.OK)
	public ResponseEntity<Adharcenter> createAdharcenter(@RequestBody AdharcenterDto adharcenterDto)throws Exception{
		Adharcenter adharcenter = this.adharcenterService.createAdharcenter(adharcenterDto);
		return new ResponseEntity<>(adharcenter,HttpStatus.CREATED);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Adharcenter> getUserById(@PathVariable int id) {
		return ResponseEntity.ok().body(this.adharcenterService.findById(id));
	} 
	@DeleteMapping(value="/{id}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public HttpStatus delete(@PathVariable int id) {
			System.out.println("delete");
			this.adharcenterService.deleteById(id);
			return HttpStatus.OK;
	        
	    }
	@PutMapping(value="/{id}")
	public ResponseEntity<Adharcenter> update(@RequestBody AdharcenterDto stadium,@PathVariable int id) {
	stadium.setId(id);
		return ResponseEntity.ok().body(adharcenterService.update(stadium));
		
		}

}
