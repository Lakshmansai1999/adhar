package com.example.demoadhar.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotNull(message = "firstName should not be empty")
    @Size(min = 2, message = "First Name should have atleast 2 characters")
    private String firstName;
	
	@NotNull(message = "lastName should not be empty")
    @Size(min = 2, message = "last Name should have atleast 2 characters")
    private String lastName;
	
	/*@NotNull(message = "username should not be empty")
    @Size(min = 2, message = "user Name should have atleast 2 characters and unique")
    private String username;*/
	
    private String password;
    
 /*  @NotBlank(message = "email should not be blank")
   // @Pattern(regexp ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",message="mail id is not valid")
    @NotNull(message = "email should not be empty")
   @Email(message = "email should be in correct format and should not be null")
    private String email;*/
   
   @Pattern(regexp ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",message="mail id is not valid")
   @NotEmpty(message = "email should not be empty")
   @Email(message = "email should be in correct format and should not be null")
   private String email;
    
    @NotEmpty(message = "role should not be empty")
    private List<String> role;
    
    
    public void setRole(List<String> role) {
		this.role = role;
	}
    
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", role=" + role + "]";
	}

}