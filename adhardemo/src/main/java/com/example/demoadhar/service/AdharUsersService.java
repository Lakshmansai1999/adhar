package com.example.demoadhar.service;

import java.util.List;
import org.springframework.stereotype.Service;



import com.example.demoadhar.dto.AdharUsersDto;
import com.example.demoadhar.entity.AdharUsers;
import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.User;


public interface AdharUsersService {
	public List<AdharUsers> getAllAdharUsers();

public AdharUsers createAdharUsers(AdharUsersDto userDto)throws Exception;

public AdharUsers findByAuid(int auid);

public void deleteByAuid(int auid);

public AdharUsers update(AdharUsersDto AdharUsersDto);

//public List<AdharUsers> getAllAdharUsers();
}

