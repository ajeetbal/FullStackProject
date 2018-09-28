package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;

public interface DistributorService {
	public List<DistributorMV> getAllCustomer();

	ResponseEntity<String> deleteCustomer(long id);

	



	DistributorMV postCustomer(DistributorVM customer);

	ResponseEntity<DistributorMV> updateCustomer(long id, DistributorVM customer);

	public List<DistributorMV> findByidpassword(long id, String password);
}
