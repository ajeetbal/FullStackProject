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
import com.mvc.mysql.repo.DistributorRepository;
import com.mvc.mysql.service.DistributorService;

//
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DistributorController {

	@Autowired
	DistributorRepository repository;

	@Autowired
	DistributorService service;

	// GET ALL PRODUCT DATA
	@GetMapping("/employees")
	public List<DistributorMV> getAllCustomers() {
		return service.getAllCustomer();
	}

	@PostMapping(value = "/employees/create")
	public DistributorMV postCustomer(@RequestBody DistributorVM customer) {

		return service.postCustomer(customer);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {

		return service.deleteCustomer(id);
	}

	@GetMapping(value = "employees/login/{id}/{password}")
	public List<DistributorMV> findByidpassword(@PathVariable long id, @PathVariable String password) {

		return service.findByidpassword(id, password);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<DistributorMV> updateCustomer(@PathVariable("id") long id, @RequestBody DistributorVM customer) {
		return service.updateCustomer(id, customer);
	}
}