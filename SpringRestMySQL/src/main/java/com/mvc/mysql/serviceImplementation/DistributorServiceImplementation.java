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
import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
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

	private String rawPassword;
	
	final static Logger logger = LoggerFactory.getLogger(DistributorServiceImplementation.class);

	
	/**
	 * @description get all distributors
	 */
	@Override
	public List<DistributorMV> getAllCustomer() throws InternalServerException,ResourceNotFound,BadRequestException {
		try {
		logger.info("get all customer...");
		List<DistributorEntity> customers = new ArrayList<>();

		DistributorRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<InventoryEntity>>() {
		}.getType();
		
			if (customers.isEmpty()) {

				throw new ResourceNotFound("Distributor not found");
			}

			else {
				return modelMapper.map(customers, listType);
			}
		}
		

		catch (Exception e) {

			throw new InternalServerException("Server Error");
		}
	}

	/**
	 * @description Create distributors
	 */
	@Override
	public DistributorMV postCustomer(DistributorVM customer) throws BadRequestException,InternalServerException {
		try {
		logger.info("create customer...");
			if (customer == null) {
				throw new BadRequestException("You can't send null in fields..");
			}
			else {
		DistributorEntity c = modelMapper.map(customer, DistributorEntity.class);
		
		String encrypted = encrypt(customer.getPassword());
		c.setPassword(encrypted);
		
		DistributorEntity _customer = DistributorRepository.save(c);
		return modelMapper.map(_customer, DistributorMV.class);
		}
		}
		catch(Exception e)
		{
			throw new InternalServerException("Internal Server Error");	
		}

	}

	
	/**
	 * @description update distributors
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<DistributorMV> updateCustomer(@PathVariable("id") long id,
			@RequestBody DistributorVM customer)throws BadRequestException,InternalServerException {
		try {
			if(customer==null)
			{
				throw new BadRequestException("You can't send null in fields..");
			}
		logger.info("Update customer...");

		Optional<DistributorEntity> customerData = DistributorRepository.findById(id);

		if (customerData.isPresent()) {
			DistributorEntity _customer = customerData.get();
			_customer.setName(customer.getName());

			return new ResponseEntity<DistributorMV>(
					(MultiValueMap<String, String>) DistributorRepository.save(_customer), HttpStatus.OK);
		} else {
			throw new ResourceNotFound("Distributor not found");
		}
		}
		catch(Exception e)
		{
			throw new InternalServerException("Internal Server Error");
		}
	}

	/**
	 * @description delete  distributor by id
	 */
	@Override
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) throws ResourceNotFound {
		logger.info("delete customer...");
		if(	DistributorRepository.existsById(id))
		{
			DistributorRepository.deleteById(id);
			return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
		
		}
		else
		{
			throw new ResourceNotFound("Distributor id not found..");

		}
		
	}

	/**
	 * @description login distributors
	 */
	@Override
	public ResponseEntity<String> loginCustomer(DistributorVM customer)throws BadRequestException,InternalServerException {
		// TODO Auto-generated method stub
		try {
			if(customer==null)
			{
				throw new BadRequestException("You can't send null in fields..");

			}
			
		logger.info("login customer...");
		List<DistributorEntity> customerData = DistributorRepository.findByidpassword(customer.getId(),
				encrypt(customer.getPassword()));
		if (customerData.isEmpty()) {
			return new ResponseEntity<>("Login Unsuccessful!", HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<>("successfully loged in!", HttpStatus.OK);
		}
		}
		catch(Exception e)
		{
			throw new InternalServerException("Internal Server Error");

		}
	}


	public String encrypt(String rawPassword) {

		this.rawPassword = rawPassword;

		return "crd56" + this.rawPassword + "!@#awfs88";
	}

}
