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

import com.mvc.mysql.entity.UserEntity;
import com.mvc.mysql.model.UserMV;
import com.mvc.mysql.model.UserVM;
import com.mvc.mysql.repo.UserRepository;
import com.mvc.mysql.service.UserService;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper mm;
	@Override
	public List<UserMV> getAllCustomer() {
		System.out.println("Get all Employees...");

		List<UserEntity> customers = new ArrayList<>();

		userRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<UserEntity>>() {
		}.getType();
		return mm.map(customers, listType);
	}

	@Override
	public UserMV postCustomer(UserVM customer) {
		// TODO Auto-generated method stub
		UserEntity c = mm.map(customer, UserEntity.class);
		UserEntity _customer = userRepository.save(c);
		return mm.map(_customer, UserMV.class);
		}

	@Override
	public ResponseEntity<String> deleteCustomer(long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		userRepository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserMV> updateCustomer(long id, UserVM customer) {
		// TODO Auto-generated method stub
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<UserEntity> customerData = userRepository.findById(id);

		if (customerData.isPresent()) {
			UserEntity _customer = customerData.get();
			//_customer.setName(customer.getName());
			//_customer.setUsername(customer.getUsername());
			return new ResponseEntity<UserMV>((MultiValueMap<String, String>) userRepository.save(_customer),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<UserMV>(HttpStatus.NOT_FOUND);
		}
	}

}
