package com.pgmanagement.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pgmanagement.dto.UserRequestDto;
import com.pgmanagement.dto.UserResponseDto;
import com.pgmanagement.entity.UserEntity;
import com.pgmanagement.enums.UserRole;
import com.pgmanagement.exception.EmailAlreadyExistException;
import com.pgmanagement.repository.UserRepository;
import com.pgmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository,
							PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder= passwordEncoder;
	}
	

	@Override
	public UserResponseDto registerUser(UserRequestDto request) {
		
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistException("Email Already Exist");
		}
		
		UserEntity user = new UserEntity();
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(UserRole.STUDENT);
		
		UserEntity savedUser = userRepository.save(user);
		
		UserResponseDto response = new UserResponseDto();
		
		response.setId(savedUser.getId());
		response.setFirstName(savedUser.getFirstName());
		response.setLastName(savedUser.getLastName());
		response.setEmail(savedUser.getEmail());
		response.setPhoneNumber(savedUser.getPhoneNumber());
		
		return response;
		
		
		
	}

}
