package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mvc.mysql.entity.InventoryEntity;
import com.mvc.mysql.entity.DistributorEntity;
import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;
import com.mvc.mysql.repo.InventoryRepository;
import com.mvc.mysql.repo.DistributorRepository;
import com.mvc.mysql.service.InventoryService;

@Service
public class InventoryServiceImplementation implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	DistributorRepository distributorRepository;

	@Autowired
	ModelMapper modelMapper;

	Date created = new Date();
	Date updated = new Date();

	@Override
	public InventoryMV postCustomer(InventoryVM customer) {

		InventoryEntity c = modelMapper.map(customer, InventoryEntity.class);
		c.setCreatedOn(created);
		c.setUpdatedOn(updated);
		Optional<DistributorEntity> employee = distributorRepository.findById(customer.getEmployeeId());
		if (!employee.isPresent()) {

		}
		c.setemployeeCategory(employee.get());
		InventoryEntity _customer = inventoryRepository.save(c);
		return modelMapper.map(_customer, InventoryMV.class);
//		EmployeeEntity employee = 
//				c.setemployeeCategory(employeeCategory);
	}

	@Override
	public ResponseEntity<String> deleteCustomer(long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		inventoryRepository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@Override
	public List<InventoryMV> getAllCustomers() {

		System.out.println("Get all Products...");

		List<InventoryEntity> customers = new ArrayList<>();

		inventoryRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<InventoryEntity>>() {
		}.getType();
		return modelMapper.map(customers, listType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<InventoryMV> updateCustomer(long id, InventoryVM customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<InventoryEntity> customerData = inventoryRepository.findById(id);

		if (customerData.isPresent()) {
			InventoryEntity _customer = customerData.get();
			_customer.setProductName(customer.getProductName());
			_customer.setUpdatedOn(updated);

			return new ResponseEntity<InventoryMV>(
					(MultiValueMap<String, String>) (MultiValueMap<String, String>) inventoryRepository.save(_customer),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<InventoryMV>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Optional<InventoryEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return inventoryRepository.findById(id);
	}
}
