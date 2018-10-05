package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.UserMV;
import com.mvc.mysql.model.UserVM;

public interface UserService  {

	/***
	 * 
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 */
	List<UserMV> getAllCustomer() throws InternalServerException,ResourceNotFound;

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	UserMV postCustomer(UserVM customer) throws BadRequestException, InternalServerException;

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
	ResponseEntity<UserMV> updateCustomer(long id, UserVM customer) throws BadRequestException, InternalServerException;

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	ResponseEntity<String> loginCustomer(UserVM customer) throws BadRequestException, InternalServerException;

	
}
