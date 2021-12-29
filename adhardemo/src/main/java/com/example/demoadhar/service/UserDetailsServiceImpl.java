package com.example.demoadhar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.demoadhar.Exception.CustomException;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.Authority;
import com.example.demoadhar.entity.Mail;
import com.example.demoadhar.entity.PasswordGenerator;
import com.example.demoadhar.entity.User;
import com.example.demoadhar.repository.AuthorityRepository;
import com.example.demoadhar.repository.UserRepository;
@Service
@ControllerAdvice
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	 private  PasswordGenerator passwordGenerator;
	
	@Autowired
	private EmailService  emailservice;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException(username);
	}
	
	@Transactional(readOnly = true)
	 public List<User> getAll() {
		 return userRepository.findAll();
	 }
	@Override
	@Transactional
	public User create(UserDto userdto) throws Exception {
		
		User user=new User();
		user.setFirstName(userdto.getFirstName());
	      user.setLastName(userdto.getLastName());
	      user.setEmail(userdto.getEmail());
	     // user.setUsername(userdto.getUsername());
	      user.setUsername(userdto.getEmail());
	      
	      
	      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	      String pass= passwordGenerator.generateRandomPassword(8);
	      String encodedPassword = passwordEncoder.encode(pass);
	      System.out.println(pass);
	      user.setPassword(encodedPassword);
	     
	      User u2=null;
	      List<Authority> addAuthorities=authorityRepository.findAll();
        
          
	        String getName=addAuthorities.get(0).getName();
	        List<String> s4=new ArrayList<String>();
	        s4.add(getName);
	        
	        List<Authority> addAuthoritiess=authorityRepository.find(userdto.getRole());
	       
	        if(s4.equals(userdto.getRole()))
	        {
	        	throw new CustomException("this role was not added ");
	        }
	        else
	        {
          user.setAuthorities(addAuthoritiess);
         u2= userRepository.save(user);
	        }
	          
          
	Mail email = new Mail();
	 email.setSubject("Welcome to mobile Project");
  email.setToEmail(user.getEmail());
  email.setContent("user name:"+user.getUsername() +"\n"+ "password :"+pass);
  emailservice.sendEmail(email);
  return u2;
 
	}
	
	@Transactional
	public User update(UserDto userdto) {
Optional<User> userdb=this.userRepository.findById((int) userdto.getId());
		
		if(userdb.isPresent()) {
			User userUpdate=userdb.get();
			userUpdate.setId(userdto.getId());
		//	userUpdate.setUsername(userdto.getUsername());
			userUpdate.setUsername(userdto.getEmail());
			userUpdate.setFirstName(userdto.getFirstName());
			userUpdate.setLastName(userdto.getLastName());
			userUpdate.setEmail(userdto.getEmail());
		    userUpdate.setPassword(userdto.getPassword());
		    userRepository.save(userUpdate);
		    return userUpdate;
		}
		else {
			throw new RuntimeException("Record not found with id" + userdto.getId());
		}
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
Optional<User> userdb=this.userRepository.findById(id);
		
		if(userdb.isPresent()) {
			
			this.userRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Record not found with id  :" +id);
		} 
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		Optional<User> userdb=this.userRepository.findById(id);
		if(userdb.isPresent()) {
			return userdb.get();
		}
		
		else {
			throw  new CustomException("Record not found with id  :" +id);
		}
		
	}
}