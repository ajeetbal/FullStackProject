package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.exception.DistributorServiceException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.UserMV;
import com.mvc.mysql.model.UserVM;

public interface UserService  {

	List<UserMV> getAllCustomer() throws DistributorServiceException,ResourceNotFound;

	UserMV postCustomer(UserVM customer);

	ResponseEntity<String> deleteCustomer(long id);

	ResponseEntity<UserMV> updateCustomer(long id, UserVM customer);

	ResponseEntity<String> loginCustomer(UserVM customer);

	
}
