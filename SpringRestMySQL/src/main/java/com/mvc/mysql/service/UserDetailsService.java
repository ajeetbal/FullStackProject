package com.mvc.mysql.service;

import java.util.List;

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.UserDetailsMV;

public interface UserDetailsService {

	/***
	 * 
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 * @throws BadRequestException
	 */
	List<UserDetailsMV> getAllCustomer() throws InternalServerException, ResourceNotFound, BadRequestException;

	/***
	 * 
	 * @param id
	 * @return
	 */
	UserDetailsMV add(Long id);

	
	

}
