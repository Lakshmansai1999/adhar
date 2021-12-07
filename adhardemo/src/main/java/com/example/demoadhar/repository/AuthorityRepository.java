package com.example.demoadhar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoadhar.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

/*	@Query(value = "SELECT * FROM Authority u where u.name = 'ADMIN' " , nativeQuery = true)
    List<Authority> find(); */
	
	@Query(value = "SELECT * FROM Authority u where u.name IN (:roles)", nativeQuery = true)
    List<Authority> find(@Param("roles") List<String> roles);
	
	
}