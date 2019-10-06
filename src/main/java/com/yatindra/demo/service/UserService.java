package com.yatindra.demo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.yatindra.demo.dto.UserDTO;

public interface UserService {

	Optional<UserDTO> getUserDetail(Long id);

	Optional<UserDTO> updateUserDetail(@Valid UserDTO user);

	void deleteUserDetail(Long id);

	UserDTO createUserDetail(@Valid UserDTO user);

	List<UserDTO> getUserDetails();

}
