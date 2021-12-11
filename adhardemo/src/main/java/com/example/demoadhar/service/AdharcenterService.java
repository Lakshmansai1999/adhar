package com.example.demoadhar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demoadhar.dto.AdharcenterDto;
import com.example.demoadhar.entity.AdharUsers;
import com.example.demoadhar.entity.Adharcenter;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.User;



@Service
public interface AdharcenterService {
public List<Adharcenter> getAllAdharcenters();
	
	public Adharcenter createAdharcenter(AdharcenterDto userDto)throws Exception;
	
	public Adharcenter findById(int id);
	
	public void deleteById(int id);
	
	public Adharcenter update(AdharcenterDto AdharcenterDto);

	List<AdharUsers> getAllAdharUsers();

}
