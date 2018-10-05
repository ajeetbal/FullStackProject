package com.mvc.mysql.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;
import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.model.UserDetailsVM;
import com.mvc.mysql.service.UserDetailsService;

@RestController
@RequestMapping("/api")
public class UserDetailsController {

	@Autowired
	UserDetailsService userDetailsService;

	/**
	 * 
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 * @throws BadRequestException
	 */
	@GetMapping("/usersDetails")
	public List<UserDetailsMV> getAllCustomers() throws InternalServerException, ResourceNotFound, BadRequestException {
		return userDetailsService.getAllCustomer();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/usersDetails/{id}")
	public UserDetailsMV add(@Valid @PathVariable("id") Long id) {
		return userDetailsService.add(id);
	}

	
}