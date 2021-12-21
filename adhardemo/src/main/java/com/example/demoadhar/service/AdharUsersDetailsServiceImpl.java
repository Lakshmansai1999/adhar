package com.example.demoadhar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoadhar.dto.AdharUsersDto;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.AdharUsers;
import com.example.demoadhar.entity.Adharcenter;
import com.example.demoadhar.entity.Authority;
import com.example.demoadhar.entity.Mail;
import com.example.demoadhar.entity.PasswordGenerator;
import com.example.demoadhar.entity.User;
import com.example.demoadhar.repository.AdharUsersRepository;
import com.example.demoadhar.repository.AdharcenterRepository;
import com.example.demoadhar.repository.AuthorityRepository;
import com.example.demoadhar.repository.UserRepository;
@Service
public class AdharUsersDetailsServiceImpl implements AdharUsersService{
	@Autowired
	private AdharcenterRepository adharcenterRepository;

	@Autowired
	private AdharUsersRepository adharusersRepository;
	
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
	 public List<AdharUsers> getAllAdharUsers() {
		 return this.adharusersRepository.findAll();
	 } 
	
	@Override
	@Transactional
public AdharUsers createAdharUsers(AdharUsersDto userDto)throws Exception{
		
		AdharUsers adharusersDto = new AdharUsers();
		adharusersDto.setFathername(userDto.getFathername());
		adharusersDto.setAge(userDto.getAge());
		adharusersDto.setMno(userDto.getMno());
		adharusersDto.setAddress(userDto.getAddress());
	   
		Adharcenter adharcenter=adharcenterRepository.findByCode(userDto.getCode());
		adharusersDto.setAdharcenter(adharcenter);
		
		
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
					 mail.setSubject("Welcome to Adhar Project");
			       mail.setToEmail(user.getEmail());
			       mail.setContent("You were " +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
			       emailService.sendEmail(mail);
				adharusersDto.setUserId(user2);
				return adharusersRepository.save(adharusersDto);
			
	}
	@Override
	@Transactional(readOnly = true)
	public AdharUsers findByAuid(int auid) {
		
		Optional<AdharUsers> stad=this.adharusersRepository.findById(auid);
		if(stad.isPresent()) {
			return stad.get();
		}
		
		else {
			throw  new RuntimeException("Record not found with center id  :" +auid);
		}
	}
	@Override
	@Transactional
	public void deleteByAuid(int auid) {
		
		Optional<AdharUsers> adharusers= this.adharusersRepository.findById(auid);
        if(adharusers.isPresent()) {
			
        	this.adharusersRepository.deleteById(auid);
		}
		else {
			throw new RuntimeException("Record not found with entre id  :" +auid);
		}
		
	}
	@Override
	@Transactional
	public AdharUsers update(AdharUsersDto adharusersDto) 
	{
	
        Optional<AdharUsers> adharusers=this.adharusersRepository.findById(adharusersDto.getAuid());
	

		
		if(adharusers.isPresent()) {
			AdharUsers adharusersUpdate=adharusers.get();
			adharusersDto.setFathername(adharusersDto.getFathername());
			adharusersDto.setAge(adharusersDto.getAge());
			adharusersDto.setMno(adharusersDto.getMno());
			adharusersDto.setAddress(adharusersDto.getAddress());
			
	
				User userUpdate=new User();
				userUpdate.setId(adharusers.get().getUserId().getId());
				userUpdate.setUsername(adharusers.get().getUserId().getUsername());
				userUpdate.setFirstName(adharusers.get().getUserId().getFirstName());
				userUpdate.setLastName(adharusers.get().getUserId().getLastName());
				userUpdate.setEmail(adharusers.get().getUserId().getEmail());
			   userUpdate.setPassword(adharusers.get().getUserId().getPassword());
			    userRepository.save(userUpdate);
			    
			    adharusersUpdate.setUserId(userUpdate);
			

			
	          this.adharusersRepository.save(adharusersUpdate);
	          return adharusersUpdate;
		}
		else {
			throw new RuntimeException("Record not found with id" + adharusersDto.getAuid());
		}
	}




}
