package com.mvc.mysql.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;
import com.mvc.mysql.service.DistributorService;

//@CrossOrigin(origins = "http://localhost:4200")
/**
 * @author ajeet.bal
 *
 */
@RestController
@RequestMapping("/api")
public class DistributorController {

	@Autowired
	DistributorService distributorService;

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping(value = "/distributors/create")
	public DistributorMV postCustomer(@Valid @RequestBody DistributorVM customer)
			throws BadRequestException, InternalServerException {
		return distributorService.postCustomer(customer);
	}

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	@DeleteMapping("/distributors/{id}")
	public ResponseEntity<String> deleteCustomer(@Valid @PathVariable("id") long id) throws ResourceNotFound {
		return distributorService.deleteCustomer(id);
	}

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping("/distributors/login")
	public ResponseEntity<String> loginCustomer(@Valid @RequestBody DistributorVM customer)
			throws BadRequestException, InternalServerException {
		return distributorService.loginCustomer(customer);
	}

	/***
	 * 
	 * @param id
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PutMapping("/distributors/{id}")
	public ResponseEntity<DistributorMV> updateCustomer(@Valid @PathVariable("id") long id,
			@RequestBody DistributorVM customer) throws BadRequestException, InternalServerException {
		return distributorService.updateCustomer(id, customer);
	}
}