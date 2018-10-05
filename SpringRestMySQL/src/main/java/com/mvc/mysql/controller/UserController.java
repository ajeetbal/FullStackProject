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
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 */
	@GetMapping("/users")
	public List<UserMV> getAllCustomers() throws InternalServerException, ResourceNotFound {
		return userService.getAllCustomer();
	}

	/**
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping(value = "/users/create")
	public UserMV postCustomer(@Valid @RequestBody UserVM customer) throws BadRequestException, InternalServerException {

		return userService.postCustomer(customer);
	}

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteCustomer(@Valid @PathVariable("id") long id) throws ResourceNotFound {

		return userService.deleteCustomer(id);
	}

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping("/users/login")
	public ResponseEntity<String> loginCustomer(@Valid @RequestBody UserVM customer) throws BadRequestException, InternalServerException {
		return userService.loginCustomer(customer);
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
	public ResponseEntity<UserMV> updateCustomer(@Valid @PathVariable("id") long id, @RequestBody UserVM customer) throws BadRequestException, InternalServerException {
		return userService.updateCustomer(id, customer);
	}

}
