package com.example.demoadhar.service;

import java.util.List;

import com.example.demoadhar.dto.UserDto;
import com.example.demoadhar.entity.User;

public interface UserService {

	public List<User> getAll();
	
	   public User create(UserDto userdto)throws Exception;
		
		public User update(UserDto userdto);
		
		 public void deleteById(int id);
		 User getUserById(int id);
	
}