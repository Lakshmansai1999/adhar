package com.example.demoadhar.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor


@Getter
@Setter

public class AdharcenterDto {
	private Integer id;
	
	@NotNull(message = "Code shoud not null")
    @Size(min = 1, message = "Code should not be null")
	private Integer code;
	
	@NotNull(message = "City shoud not null")
    @Size(min = 2, message = "City should not be null")
	private String city;
	
	private UserDto userDto;
	
	private AdharcenterDto (){
		
		
	}
	@Override
	public String toString() {
		return "AdharcenterDto [center_id=" + id + ", code=" + code + ", city=" + city + ", userDto=" + userDto
				+ "]";
	
	}
}
