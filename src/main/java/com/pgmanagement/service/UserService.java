package com.pgmanagement.service;

import com.pgmanagement.dto.UserRequestDto;
import com.pgmanagement.dto.UserResponseDto;

public interface UserService {

	UserResponseDto registerUser(UserRequestDto request);
	
}
