package com.example.demoadhar.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Mail {

	 private   String toEmail;
	 private   String subject;
	 private    String content;
	 Map<String, Object> model;
   
    
    
    
   
 
    
}