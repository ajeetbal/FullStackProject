package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

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
	InventoryRepository repository;

	@Autowired
	DistributorRepository repo;
	
	@Autowired
	ModelMapper mm;

	
	@Override
	public InventoryMV postCustomer(InventoryVM customer) {

		InventoryEntity c = mm.map(customer, InventoryEntity.class);
		
		Optional<DistributorEntity> employee = repo.findById(customer.getEmployeeId());
		if(!employee.isPresent()) {
			
		}
		c.setemployeeCategory(employee.get());
		InventoryEntity _customer = repository.save(c);
		return mm.map(_customer, InventoryMV.class);
//		EmployeeEntity employee = 
//				c.setemployeeCategory(employeeCategory);
	}

	@Override
	public ResponseEntity<String> deleteCustomer(long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@Override
	public List<InventoryMV> getAllCustomers() {

		
		
		System.out.println("Get all Products...");

		List<InventoryEntity> customers = new ArrayList<>();

		repository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<InventoryEntity>>() {
		}.getType();
		return mm.map(customers, listType);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<InventoryMV> updateCustomer(long id, InventoryVM customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<InventoryEntity> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			InventoryEntity _customer = customerData.get();
			
			
			_customer.setProductName(customer.getProductName());
			return new ResponseEntity<InventoryMV>((MultiValueMap<String, String>) repository.save(_customer),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<InventoryMV>(HttpStatus.NOT_FOUND);
		}
	}
}
