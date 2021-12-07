package com.example.demoadhar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demoadhar.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	 @Query("SELECT DISTINCT user FROM User user " +
	            "INNER JOIN FETCH user.authorities AS authorities " +
	            "WHERE user.username = :username")
	public    User findByUsername(@Param("username") String username);

}
	 	