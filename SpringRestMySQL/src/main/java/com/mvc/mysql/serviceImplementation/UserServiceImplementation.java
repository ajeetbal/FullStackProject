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

import com.mvc.mysql.entity.UserEntity;
import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.UserMV;
import com.mvc.mysql.model.UserVM;
import com.mvc.mysql.repo.UserRepository;
import com.mvc.mysql.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	final static Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	@Autowired
	private UserRepository userRepository;

	private String rawPassword;

	@Autowired
	ModelMapper modelMapper;

	String encrypted;

	/**
	 * @description Get all users
	 */
	@Override
	public List<UserMV> getAllCustomer() throws InternalServerException, ResourceNotFound {
		logger.info("get all users");
		List<UserEntity> customers = new ArrayList<>();
		userRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<UserEntity>>() {
		}.getType();
		try {
			if (customers.isEmpty()) {
				throw new ResourceNotFound("Distributor not found");
			}
			return modelMapper.map(customers, listType);
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Exception while getting exception");
		}
	}


	/**
	 * @description Create  users
	 */
	@Override
	public UserMV postCustomer(UserVM customer) throws BadRequestException,InternalServerException {
		// TODO Auto-generated method stub
	try {	logger.info("Create users");
	if (customer == null) {
		throw new BadRequestException("You can't send null in fields..");
	}
	else {
		UserEntity c = modelMapper.map(customer, UserEntity.class);
		String encrypted = encrypt(customer.getPassword());
		c.setPassword(encrypted);
		UserEntity _customer = userRepository.save(c);

		return modelMapper.map(_customer, UserMV.class);
	}
	}
	catch(Exception e)
	{
		throw new InternalServerException("Internal Server Exception while getting exception");	
	}
	}

	/**
	 * @description Delete  users by id
	 */
	@Override
	public ResponseEntity<String> deleteCustomer(long id)throws ResourceNotFound {
		
		logger.info("delete user having"+id);
 if(userRepository.existsById(id))
		{userRepository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}
 else
 {
	 throw new ResourceNotFound("user id not found..");
 }
	}


	/**
	 * @description update  users
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<UserMV> updateCustomer(long id, UserVM customer) throws BadRequestException,InternalServerException{
		// TODO Auto-generated method stub
		logger.info("Update users");
		try {
			if(customer==null)
			{
				throw new BadRequestException("You can't send null in fields..");
			}
		Optional<UserEntity> customerData = userRepository.findById(id);
		if (customerData.isPresent()) {
			UserEntity _customer = customerData.get();
			_customer.setName(customer.getName());
			_customer.setUsername(customer.getUsername());

			return new ResponseEntity<UserMV>((MultiValueMap<String, String>) userRepository.save(_customer),
					HttpStatus.OK);
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
	 * @description Login  users
	 */
	@Override
	public ResponseEntity<String> loginCustomer(UserVM customer)throws BadRequestException,InternalServerException  {
		logger.info("LogIn users");
		try {
			if(customer==null)
			{
				throw new BadRequestException("You can't send null in fields..");

			}
		List<UserEntity> customerData = userRepository.findByIdAndPassword(customer.getId(),
				encrypt(customer.getPassword()));
		if (customerData.isEmpty()) {
			return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
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
