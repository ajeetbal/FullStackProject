package com.mvc.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;
import com.mvc.mysql.service.DistributorService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DistributorController {

	@Autowired
	DistributorService distributorService;

	@PostMapping(value = "/distributors/create")
	public DistributorMV postCustomer(@RequestBody DistributorVM customer) {
		return distributorService.postCustomer(customer);
	}

	@DeleteMapping("/distributors/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		return distributorService.deleteCustomer(id);
	}

	@PostMapping("/distributors/login")
	public ResponseEntity<String> loginCustomer(@RequestBody DistributorVM customer) {
		return distributorService.loginCustomer(customer);
	}

	@PutMapping("/distributors/{id}")
	public ResponseEntity<DistributorMV> updateCustomer(@PathVariable("id") long id,
			@RequestBody DistributorVM customer) {
		return distributorService.updateCustomer(id, customer);
	}
}