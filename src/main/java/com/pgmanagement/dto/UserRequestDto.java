package com.pgmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

	@NotBlank(message = "First Name is Required")
	private String firstName;
	
	@NotBlank(message = "Last Name is Required")
	private String lastName;
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid Email Format")
	private String email;
	
	@NotBlank(message = "Phone Number is Required")
	private String phoneNumber;
	
	@NotBlank(message = "Password is Required")
	@Size(min = 6,message = "Password must be contain at least 6 characters")
	private String password;
	
}
