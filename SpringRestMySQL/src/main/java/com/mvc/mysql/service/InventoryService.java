package com.mvc.mysql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.entity.InventoryEntity;
import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;

public interface InventoryService {

	/***
	 * 
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 * @throws BadRequestException
	 */
	List<InventoryMV> getAllCustomers() throws InternalServerException, ResourceNotFound, BadRequestException;

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	InventoryMV postCustomer(InventoryVM customer) throws BadRequestException, InternalServerException;

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	ResponseEntity<String> deleteCustomer(long id) throws ResourceNotFound;

	/***
	 * 
	 * @param id
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	ResponseEntity<InventoryMV> updateCustomer(long id, InventoryVM customer) throws BadRequestException, InternalServerException;

	/***
	 * 
	 * @param id
	 * @return
	 */
	Optional<InventoryEntity> findById(Long id);

}
