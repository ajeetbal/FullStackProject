package com.mvc.mysql.controller;

import java.util.List;

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

import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;
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
	
	@GetMapping("/users")
	public List<UserMV> getAllCustomers() {
		return userService.getAllCustomer();
	}

	@PostMapping(value = "/users/create")
	public UserMV postCustomer(@RequestBody UserVM customer) {

		return userService.postCustomer(customer);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {

		return userService.deleteCustomer(id);
	}

//	@GetMapping(value = "employees/login/{id}/{password}")
//	public List<UserMV> findByidpassword(@PathVariable long id, @PathVariable String password) {
//
//		return userService.findByidpassword(id, password);
//	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserMV> updateCustomer(@PathVariable("id") long id, @RequestBody UserVM customer) {
		return userService.updateCustomer(id, customer);
	}
}
