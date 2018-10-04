package com.mvc.mysql.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.service.UserDetailsService;

@RestController
@RequestMapping("/api")
public class UserDetailsController {

	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping("/usersDetails")
	public List<UserDetailsMV> getAllCustomers() {
		return userDetailsService.getAllCustomer();
	}

	@GetMapping("/usersDetails/{id}")
	public UserDetailsMV add(@PathVariable("id") Long id) {
		return userDetailsService.add(id);
	}

}