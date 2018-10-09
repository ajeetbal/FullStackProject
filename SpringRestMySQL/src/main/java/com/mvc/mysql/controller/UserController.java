package com.mvc.mysql.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.UserMV;
import com.mvc.mysql.model.UserVM;
import com.mvc.mysql.repo.UserRepository;
import com.mvc.mysql.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	/**
	 * 
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 * 
	 * @return UserMV
	 */
	@GetMapping("/users")
	public List<UserMV> getAllUsers() throws InternalServerException, ResourceNotFound {
		return userService.getAllUsers();
	}

	/**
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping(value = "/users/create")
	public ResponseEntity<UserMV> postUsers(@Valid @RequestBody UserVM users) throws BadRequestException, InternalServerException {

		return userService.postUsers(users);
	}

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUsers(@Valid @PathVariable("id") long id) throws ResourceNotFound {

		return userService.deleteUsers(id);
	}

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping("/users/login")
	public ResponseEntity<String> loginUsers(@Valid @RequestBody UserVM users) throws BadRequestException, InternalServerException {
		return userService.loginUsers(users);
	}

	/***
	 * 
	 * @param id
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<UserMV> updateUsers(@Valid @PathVariable("id") long id, @RequestBody UserVM users) throws BadRequestException, InternalServerException {
		return userService.updateUsers(id, users);
	}

}
