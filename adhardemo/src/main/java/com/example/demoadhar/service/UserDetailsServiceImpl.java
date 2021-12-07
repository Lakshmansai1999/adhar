package com.example.demoadhar.service;

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
	public User saveUser(UserDto userdto) throws Exception {
		
		User user=new User();
		user.setFirstName(userdto.getFirstName());
	      user.setLastName(userdto.getLastName());
	      user.setEmail(userdto.getEmail());
	      user.setUsername(userdto.getUsername());
	      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	      String pass= passwordGenerator.generateRandomPassword(8);
	      String encodedPassword = passwordEncoder.encode(pass);
	      System.out.println(pass);
	      
	      user.setPassword(encodedPassword);
	     
	      List<Authority> addAuthorities=authorityRepository.find(userdto.getRole());
          user.setAuthorities(addAuthorities); 
         
          
	Mail mail = new Mail();
	 mail.setSubject("Welcome to adhardemo Project");
  mail.setToEmail(user.getEmail());
  mail.setContent(" User name:"+user.getUsername() +"\n"+ "password :"+pass);
  emailservice.sendEmail(mail);
 return 	userRepository.save(user);
 
	}
	
	@Transactional
	public User update(User userdto) {
		return userRepository.save(userdto);
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

	
	
}