package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mvc.mysql.entity.DistributorEntity;
import com.mvc.mysql.entity.InventoryEntity;
import com.mvc.mysql.exception.DistributorServiceException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.DistributorMV;
import com.mvc.mysql.model.DistributorVM;
import com.mvc.mysql.repo.DistributorRepository;
import com.mvc.mysql.service.DistributorService;

@Service("DistributorService")
public class DistributorServiceImplementation implements DistributorService {
	@Autowired
	DistributorRepository DistributorRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	DistributorService service;

	final static Logger logger = LoggerFactory.getLogger(DistributorServiceImplementation.class);

	@Override
	public List<DistributorMV> getAllCustomer() throws DistributorServiceException, ResourceNotFound {

		logger.info("hello");
		List<DistributorEntity> customers = new ArrayList<>();

		DistributorRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<InventoryEntity>>() {
		}.getType();
		try {
			if (customers.isEmpty()) {

				throw new ResourceNotFound("Distributor not found");
			}

			else {
				return modelMapper.map(customers, listType);
			}

		}

		catch (Exception e) {
			throw new DistributorServiceException("Internal Server Exception while getting exception");
		}

	}

	@Override
	public DistributorMV postCustomer(DistributorVM customer) {
		DistributorEntity c = modelMapper.map(customer, DistributorEntity.class);

		DistributorEntity _customer = DistributorRepository.save(c);

		return modelMapper.map(_customer, DistributorMV.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<DistributorMV> updateCustomer(@PathVariable("id") long id,
			@RequestBody DistributorVM customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<DistributorEntity> customerData = DistributorRepository.findById(id);

		if (customerData.isPresent()) {
			DistributorEntity _customer = customerData.get();
			_customer.setName(customer.getName());

			// _customer.setPassword(customer.getPassword());
//			
			return new ResponseEntity<DistributorMV>(
					(MultiValueMap<String, String>) DistributorRepository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<DistributorMV>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		DistributorRepository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> loginCustomer(DistributorVM customer) {
		// TODO Auto-generated method stub
		List<DistributorEntity> customerData = DistributorRepository.findByidpassword(customer.getId(),
				customer.getPassword());
		if (customerData.isEmpty()) {
			return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<>("successfully loged in!", HttpStatus.OK);
		}
	}

	@Override
	public List<DistributorMV> getCustomerNull() throws DistributorServiceException, ResourceNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DistributorMV> getCustomerException() throws DistributorServiceException, ResourceNotFound {
		// TODO Auto-generated method stub
		throw new DistributorServiceException();
	}

}
