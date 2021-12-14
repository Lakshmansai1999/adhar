package com.example.demoadhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.example.demoadhar.entity.Adharcenter;

@Repository
public interface AdharcenterRepository extends JpaRepository<Adharcenter, Integer>{
	
	Adharcenter findByCode(int code);
	
}
