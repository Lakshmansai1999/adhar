package com.example.demoadhar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.User;
import com.example.demoadhar.repository.UserRepository;
import com.example.demoadhar.service.UserDetailsServiceImpl;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	 private UserDetailsServiceImpl detailsServiceImpl; 
		
		public UserController(UserDetailsServiceImpl detailsServiceImpl) {
			this.detailsServiceImpl = detailsServiceImpl;
		} 
		
	@GetMapping(value="/get")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
    }
	
	/*@RequestMapping(value="/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody UserDto user) throws Exception {
        detailsServiceImpl.create(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }*/
	
	@PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) throws Exception {
		
		User newUser=detailsServiceImpl.saveUser(user);
		return new ResponseEntity<>( newUser,HttpStatus.CREATED);
        
    }
	
	
	@DeleteMapping("/user/{id}")
	public HttpStatus deleteById(@PathVariable int id){
		this.detailsServiceImpl.deleteById(id);
		return HttpStatus.OK;
	}
	
	@PutMapping(value="/user/{id}")
	public ResponseEntity<User> update(@RequestBody User users,@PathVariable Integer id) {
		users.setId(id);
		//return detailsServiceImpl.update(users);
		return ResponseEntity.ok().body(detailsServiceImpl.update(users));
		
		}
}