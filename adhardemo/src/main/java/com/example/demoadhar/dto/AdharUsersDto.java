package com.example.demoadhar.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor

@Getter
@Setter

public class AdharUsersDto {

	private Integer auid;
	private String fathername;
	private Integer age;
	private Long mno;
	private String address;
	private UserDto userDto;
	private Integer code;
	private AdharUsersDto() {}
	
	@Override
	public String toString() {
		return "AdhaeUsersDto [auid=" + auid + ", fathername=" + fathername + ", age=" + age + ",mno=" + mno + ",address=" + address + ",userDto=" + userDto + "]";
	}

}
