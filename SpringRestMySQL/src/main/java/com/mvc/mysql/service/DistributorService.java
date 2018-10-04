package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.exception.DistributorServiceException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;

public interface DistributorService {
	public List<DistributorMV> getAllCustomer() throws DistributorServiceException, ResourceNotFound;

	ResponseEntity<String> deleteCustomer(long id);

	DistributorMV postCustomer(DistributorVM customer);

	ResponseEntity<DistributorMV> updateCustomer(long id, DistributorVM customer);

	public ResponseEntity<String> loginCustomer(DistributorVM customer);

	List<DistributorMV> getCustomerNull() throws DistributorServiceException, ResourceNotFound;

	List<DistributorMV> getCustomerException() throws DistributorServiceException, ResourceNotFound;

}
