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

import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.model.UserDetailsVM;
import com.mvc.mysql.model.UserMV;
import com.mvc.mysql.model.UserVM;
import com.mvc.mysql.repo.UserDetailsRepository;
import com.mvc.mysql.service.UserDetailsService;

@RestController
@RequestMapping("/api")
public class UserDetailsController {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@GetMapping("/usersDetails")
	public List<UserDetailsMV> getAllCustomers() {
		return userDetailsService.getAllCustomer();
	}

	@PostMapping(value = "/usersDetails/create")
	public UserDetailsMV postCustomer(@RequestBody UserDetailsVM customer) {

		return userDetailsService.postCustomer(customer);
	}

	@DeleteMapping("/usersDetails/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {

		return userDetailsService.deleteCustomer(id);
	}

//	@GetMapping(value = "employees/login/{id}/{password}")
//	public List<UserMV> findByidpassword(@PathVariable long id, @PathVariable String password) {
//
//		return userService.findByidpassword(id, password);
//	}

	@PutMapping("/usersDetails/{id}")
	public ResponseEntity<UserDetailsMV> updateCustomer(@PathVariable("id") long id, @RequestBody UserDetailsVM customer) {
		return userDetailsService.updateCustomer(id, customer);
	}
}
