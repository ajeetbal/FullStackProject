package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;

public interface DistributorService {

	/***
	 * 
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 * @throws BadRequestException
	 */
	public List<DistributorMV> getAllDistributor() throws InternalServerException, ResourceNotFound, BadRequestException;

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	public ResponseEntity<String> deleteDistributor(long id) throws ResourceNotFound;

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	public ResponseEntity<DistributorMV> postDistributor(DistributorVM distributor) throws BadRequestException, InternalServerException;

	/***
	 * 
	 * @param id
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	public ResponseEntity<DistributorMV> updateDistributor(long id, DistributorVM distributor) throws BadRequestException, InternalServerException;

	
	public ResponseEntity<String> loginDistributor(DistributorVM distributor) throws BadRequestException, InternalServerException;

	
}
