package com.example.demoadhar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoadhar.dto.AdharcenterDto;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.Authority;
import com.example.demoadhar.entity.Mail;
import com.example.demoadhar.entity.PasswordGenerator;
import com.example.demoadhar.entity.Adharcenter;
import com.example.demoadhar.entity.User;
import com.example.demoadhar.repository.AuthorityRepository;
import com.example.demoadhar.repository.AdharcenterRepository;
import com.example.demoadhar.repository.UserRepository;
import com.example.demoadhar.service.EmailService;


@Service

public class AdharcenterDetailsServiceImpl implements AdharcenterService {
	@Autowired
	private AdharcenterRepository adharcenterRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	@Transactional(readOnly = true)
	 public List<Adharcenter> getAllAdharcenters() {
		 return this.adharcenterRepository.findAll();
	 } 
	
	@Override
	@Transactional
public Adharcenter createAdharcenter(AdharcenterDto userDto)throws Exception{
		
		Adharcenter adharcenterDto = new Adharcenter();
				adharcenterDto.setCity(userDto.getCity());
		adharcenterDto.setCode(userDto.getCode());
		
		UserDto dto = userDto.getUserDto();
		User user=new User();
	      user.setFirstName(dto.getFirstName());
	      user.setLastName(dto.getLastName());
	      user.setEmail(dto.getEmail());
	      user.setUsername(dto.getUsername());
	      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	      String pass=passwordGenerator.generateRandomPassword(8);
	      String encodedPassword = passwordEncoder.encode(pass);
	      System.out.println(pass);
	      user.setPassword(encodedPassword);
	      
	      @SuppressWarnings("unused")
			List<Authority> listAuList=authorityRepository.findAll();
		      @SuppressWarnings("unused")
			List<Authority> addList=authorityRepository.find(dto.getRole());
		      User user2=null;
		      List<Authority> listAll=authorityRepository.findAll();
			     String superAdmin=listAll.get(0).getName();
			     List<String> superList=new ArrayList<>();
			     superList.add(superAdmin);
			     
			     List<Authority> addAuthorities=authorityRepository.find(dto.getRole());
			    
			     if(superList.equals(dto.getRole()))
			     {
			     	throw new RuntimeException("this role was not added ");
			     }
			     else
			     {
			     user.setAuthorities(addAuthorities);
			    user2= userRepository.save(user);
			     }
			        Mail mail = new Mail();
					 mail.setSubject("Welcome to Cricket-IPL Project");
			       mail.setToEmail(user.getEmail());
			       mail.setContent("You were " +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
			       emailService.sendEmail(mail);
				adharcenterDto.setUserId(user2);
				return adharcenterRepository.save(adharcenterDto);
			
	}
	@Override
	@Transactional(readOnly = true)
	public Adharcenter findById(int id) {
		
		Optional<Adharcenter> stad=this.adharcenterRepository.findById(id);
		if(stad.isPresent()) {
			return stad.get();
		}
		
		else {
			throw  new RuntimeException("Record not found with id  :" +id);
		}
	}
	@Override
	@Transactional
	public void deleteById(int id) {
		
		Optional<Adharcenter> stadium= this.adharcenterRepository.findById(id);
        if(stadium.isPresent()) {
			
        	this.adharcenterRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Record not found with id  :" +id);
		}
		
	}
	@Override
	@Transactional
	public Adharcenter update(AdharcenterDto adharcenterDto) 
	{
	
        Optional<Adharcenter> adharcenter=this.adharcenterRepository.findById(adharcenterDto.getId());
	

		
		if(adharcenter.isPresent()) {
			Adharcenter adharcenterUpdate=adharcenter.get();
			adharcenterUpdate.setCode(adharcenterDto.getCode());
			
			//adharcenterUpdate.setCity(adharcenterDto.getCity());
			
	
				User userUpdate=new User();
				userUpdate.setId(adharcenter.get().getUserId().getId());
				userUpdate.setUsername(adharcenter.get().getUserId().getUsername());
				userUpdate.setFirstName(adharcenter.get().getUserId().getFirstName());
				userUpdate.setLastName(adharcenter.get().getUserId().getLastName());
				userUpdate.setEmail(adharcenter.get().getUserId().getEmail());
			   userUpdate.setPassword(adharcenter.get().getUserId().getPassword());
			    userRepository.save(userUpdate);
			    
			    adharcenterUpdate.setUserId(userUpdate);
			

			
	          this.adharcenterRepository.save(adharcenterUpdate);
	          return adharcenterUpdate;
		}
		else {
			throw new RuntimeException("Record not found with id" + adharcenterDto.getId());
		}
	}


}
