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
import com.mvc.mysql.exception.DistributorServiceException;
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

	@Override
	public List<UserMV> getAllCustomer() throws DistributorServiceException, ResourceNotFound {

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
			throw new DistributorServiceException("Internal Server Exception while getting exception");
		}
	}

	@Override
	public UserMV postCustomer(UserVM customer) {
		// TODO Auto-generated method stub
		UserEntity c = modelMapper.map(customer, UserEntity.class);
		String encrypted = encrypt(customer.getPassword());
		c.setPassword(encrypted);
		UserEntity _customer = userRepository.save(c);

		return modelMapper.map(_customer, UserMV.class);
	}

	@Override
	public ResponseEntity<String> deleteCustomer(long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		userRepository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<UserMV> updateCustomer(long id, UserVM customer) {
		// TODO Auto-generated method stub
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<UserEntity> customerData = userRepository.findById(id);

		if (customerData.isPresent()) {
			UserEntity _customer = customerData.get();
			_customer.setName(customer.getName());
			_customer.setUsername(customer.getUsername());

			return new ResponseEntity<UserMV>((MultiValueMap<String, String>) userRepository.save(_customer),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<UserMV>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> loginCustomer(UserVM customer) {

		List<UserEntity> customerData = userRepository.findByIdAndPassword(customer.getId(),
				encrypt(customer.getPassword()));

		if (customerData.isEmpty()) {
			return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<>("successfully loged in!", HttpStatus.OK);
		}
	}

	public String encrypt(String rawPassword) {

		this.rawPassword = rawPassword;

		return "crd56" + this.rawPassword + "!@#awfs88";
	}

}
