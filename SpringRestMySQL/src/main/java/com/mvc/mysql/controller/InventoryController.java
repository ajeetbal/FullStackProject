package com.mvc.mysql.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;
import com.mvc.mysql.service.InventoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	/***
	 * 
	 * @return
	 * @throws InternalServerException
	 * @throws ResourceNotFound
	 * @throws BadRequestException
	 */
	@GetMapping("/inventory")
	public List<InventoryMV> getAllInventory() throws InternalServerException, ResourceNotFound, BadRequestException {

		return inventoryService.getAllInventory();
	}

	/***
	 * 
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PostMapping(value = "/inventory/create")
	public ResponseEntity<InventoryMV> postInventory(@Valid @RequestBody InventoryVM inventory)
			throws BadRequestException, InternalServerException {

		return inventoryService.postInventory(inventory);
	}

	/***
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFound
	 */
	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<String> deleteInventory(@Valid @PathVariable("id") long id) throws ResourceNotFound {

		return inventoryService.deleteInventory(id);
	}

	/***
	 * 
	 * @param id
	 * @param customer
	 * @return
	 * @throws BadRequestException
	 * @throws InternalServerException
	 */
	@PutMapping("/inventory/{id}")
	public ResponseEntity<InventoryMV> updateInventory(@Valid @PathVariable("id") long id,
			@RequestBody InventoryVM inventory) throws BadRequestException, InternalServerException {
		return inventoryService.updateInventory(id,inventory);
	}

}
