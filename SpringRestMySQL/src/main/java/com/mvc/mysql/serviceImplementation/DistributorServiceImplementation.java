package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mvc.mysql.entity.DistributorEntity;
import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;
import com.mvc.mysql.repo.DistributorRepository;
import com.mvc.mysql.service.DistributorService;

@Service("DistributorService")
public class DistributorServiceImplementation implements DistributorService {
	@Autowired
	DistributorRepository repository;

	@Autowired
	ModelMapper mm;

//	@Autowired
//	private PasswordEncoder encrypt;
	
	
	@Override
	public List<DistributorMV> getAllCustomer() {

		System.out.println("Get all Employees...");

		List<DistributorEntity> customers = new ArrayList<>();

		repository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<DistributorEntity>>() {
		}.getType();
		return mm.map(customers, listType);

	}

	@Override
	public DistributorMV postCustomer(DistributorVM customer) {
		DistributorEntity c = mm.map(customer, DistributorEntity.class);
		//encrypt.encode(c.getPassword());
		DistributorEntity _customer = repository.save(c);
		
		return mm.map(_customer, DistributorMV.class);

	}

	@Override
	public ResponseEntity<DistributorMV> updateCustomer(@PathVariable("id") long id, @RequestBody DistributorVM customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<DistributorEntity> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			DistributorEntity _customer = customerData.get();
			_customer.setName(customer.getName());

			return new ResponseEntity<DistributorMV>((MultiValueMap<String, String>) repository.save(_customer),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<DistributorMV>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<DistributorMV> findByidpassword(@PathVariable long id, @PathVariable String password) {

		List<DistributorEntity> customers = repository.findByidpassword(id, password);
		Type listType = new TypeToken<List<DistributorEntity>>() {
		}.getType();
		return mm.map(customers, listType);
	}

	@Override
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

}
