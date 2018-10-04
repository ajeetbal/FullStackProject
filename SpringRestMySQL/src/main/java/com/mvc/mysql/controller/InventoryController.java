package com.mvc.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;
import com.mvc.mysql.service.InventoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@GetMapping("/inventory")
	public List<InventoryMV> getAllCustomers() {

		return inventoryService.getAllCustomers();
	}

	@PostMapping(value = "/inventory/create")
	public InventoryMV postCustomer(@RequestBody InventoryVM customer) {

		return inventoryService.postCustomer(customer);
	}

	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {

		return inventoryService.deleteCustomer(id);
	}

	@PutMapping("/inventory/{id}")
	public ResponseEntity<InventoryMV> updateCustomer(@PathVariable("id") long id, @RequestBody InventoryVM customer) {
		return inventoryService.updateCustomer(id, customer);
	}

}
