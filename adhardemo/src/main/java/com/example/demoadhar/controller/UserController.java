package com.example.demoadhar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.example.demoadhar.Exception.ResourceNotFoundException;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.User;
import com.example.demoadhar.repository.UserRepository;
import com.example.demoadhar.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	 private UserDetailsServiceImpl detailsServiceImpl; 
	@Autowired
	private UserRepository userRepository;
		
		public UserController(UserDetailsServiceImpl detailsServiceImpl) {
			this.detailsServiceImpl = detailsServiceImpl;
		} 
		
	@GetMapping(value="/get")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
	}
        
        @GetMapping("/{id}")
    	public ResponseEntity<User> getUserById(@PathVariable int id) {
    		 User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
    		return ResponseEntity.ok(u);
    	}
    
	
	/*@RequestMapping(value="/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody UserDto user) throws Exception {
        detailsServiceImpl.create(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }*/
	
	@RequestMapping(value="/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) throws Exception{
        detailsServiceImpl.create(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);   
    } 
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update(@RequestBody UserDto users,@PathVariable int id) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		users.setId(id);
		//return ResponseEntity.ok().body(detailsServiceImpl.update(users));		
		User updatedUser = userRepository.save(u);
		return ResponseEntity.ok(updatedUser);	
	}
	
	
	/*@DeleteMapping("/user/{id}")
	public HttpStatus deleteById(@PathVariable int id){
		this.detailsServiceImpl.deleteById(id);
		return HttpStatus.OK;
	}*/
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable int id){
		User employee = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
		userRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	/*@PutMapping(value="/user/{id}")
	public ResponseEntity<User> update(@RequestBody User users,@PathVariable Integer id) {
		users.setId(id);
		//return detailsServiceImpl.update(users);
		return ResponseEntity.ok().body(detailsServiceImpl.update(users));
		
		}*/
}