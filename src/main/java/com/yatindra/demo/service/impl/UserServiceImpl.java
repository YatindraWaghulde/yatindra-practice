package com.yatindra.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatindra.demo.dto.UserDTO;
import com.yatindra.demo.repository.UserRepository;
import com.yatindra.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public Optional<UserDTO> getUserDetail(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public List<UserDTO> getUserDetails() {
		return userRepository.findAll();
	}

	public UserDTO updateUserDetail(UserDTO user) {
		return userRepository.save(user);
	}

	public void deleteUserDetail(Long id) {
		 userRepository.deleteById(id);
	}

	public UserDTO createUserDetail(UserDTO user) {
		return userRepository.save(user);
	}

	
}
