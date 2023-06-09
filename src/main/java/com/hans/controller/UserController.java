package com.hans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hans.model.User;
import com.hans.service.UserService;

@RestController
@RequestMapping("/deviceManagement/users")
public class UserController {

	@Autowired UserService userService;
	
	@GetMapping()
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<User>> getAllUsersPage(Pageable p) {
		return new ResponseEntity<>(userService.findAllUsersPage(p), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<User> updateDevices(@PathVariable Long id) {
			return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);			
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody User u, @PathVariable Long id) {
		if(id==u.getId()) {
			return new ResponseEntity<>(userService.updateUser(u), HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERROR!! The user passed is not the same as the id specified in the URL", HttpStatus.BAD_REQUEST);			
	
	}
	
}
