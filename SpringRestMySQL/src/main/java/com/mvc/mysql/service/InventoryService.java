package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;

public interface InventoryService {

	List<InventoryMV> getAllCustomers();

	InventoryMV postCustomer(InventoryVM customer);

	ResponseEntity<String> deleteCustomer(long id);

	ResponseEntity<InventoryMV> updateCustomer(long id, InventoryVM customer);

}
