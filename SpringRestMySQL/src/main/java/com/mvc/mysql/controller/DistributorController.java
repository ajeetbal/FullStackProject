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
	public ResponseEntity<DistributorMV> postDistributor(@Valid @RequestBody DistributorVM distributor)
			throws BadRequestException, InternalServerException {
		return distributorService.postDistributor(distributor);
	}

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	@DeleteMapping("/distributors/{id}")
	public ResponseEntity<String> deleteDistributor(@Valid @PathVariable("id") long id) throws ResourceNotFound {
		return distributorService.deleteDistributor(id);
	}

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping("/distributors/login")
	public ResponseEntity<String> loginDistributor(@Valid @RequestBody DistributorVM distributor)
			throws BadRequestException, InternalServerException {
		return distributorService.loginDistributor(distributor);
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
	public ResponseEntity<DistributorMV> updateDistributor(@Valid @PathVariable("id") long id,
			@RequestBody DistributorVM distributor) throws BadRequestException, InternalServerException {
		return distributorService.updateDistributor(id, distributor);
	}
}