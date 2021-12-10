package com.example.demoadhar.service;

import java.util.List;
import org.springframework.stereotype.Service;



import com.example.demoadhar.dto.AdharUsersDto;
import com.example.demoadhar.entity.AdharUsers;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.User;

@Service
public interface AdharUsersService {
	public List<AdharUsers> getAllAdharUsers();

public AdharUsers createAdharUsers(AdharUsersDto userDto)throws Exception;

public AdharUsers findById(int id);

public void deleteById(int id);

public AdharUsers update(AdharUsersDto AdharUsersDto);

}