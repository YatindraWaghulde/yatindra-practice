package com.yatindra.demo.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yatindra.demo.dto.UserDTO;
import com.yatindra.demo.exception.CustomeException;
import com.yatindra.demo.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/userdetails/{id}",produces = "application/json")
	public ResponseEntity<UserDTO> userDetail(@PathVariable Long id) throws CustomeException {
		Optional<UserDTO> optUser = userService.getUserDetail(id); 
		if(!optUser.isPresent()) {
			throw new CustomeException(404,Arrays.asList("User not found with id : "+id));
		}
		return new ResponseEntity<UserDTO>(optUser.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/userdetails",produces = "application/json")
	public ResponseEntity<List<UserDTO>> userDetails() throws CustomeException {
		List<UserDTO> userList = userService.getUserDetails(); 
		if(null == userList) {
			throw new CustomeException(404,Arrays.asList("Users not found."));
		}
		return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
	}
	
	@PutMapping(value = "/userdetails",produces = "application/json")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO user) throws CustomeException {
		Optional<UserDTO> optUser = userService.getUserDetail(user.getUserSeq());
		if(!optUser.isPresent()) {
			throw new CustomeException(404,Arrays.asList("User not found with id :: "+user.getUserSeq()));
		}
		
		Optional<UserDTO> userDTO = userService.updateUserDetail(user); 
		
		return new ResponseEntity<UserDTO>(userDTO.get(), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/userdetails/{id}",produces = "application/json")
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) throws CustomeException {
		if(userService.getUserDetail(id).isPresent()) {
			userService.deleteUserDetail(id); 
			return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
		}
		
		throw new CustomeException(404,Arrays.asList("User not found with id :: "+id));
	}
	
	@PostMapping(value = "/userdetails",produces = "application/json")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
		user = userService.createUserDetail(user); 
		
		return ResponseEntity
	            .created(URI.create("/api/userdetails/" + user.getUserSeq()))
	            .body(user);
//		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
}
