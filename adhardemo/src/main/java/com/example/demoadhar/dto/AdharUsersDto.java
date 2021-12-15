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
	private Integer center_id;
	private UserDto userDto;
	private AdharUsersDto() {}
	@Override
	public String toString() {
		return "AdhaeUsersDto [auid=" + auid + ", fathername=" + fathername + ", age=" + age + ",mno=" + mno + ",address=" + address + ",center_id="+center_id+",userDto=" + userDto + "]";
	}

}
