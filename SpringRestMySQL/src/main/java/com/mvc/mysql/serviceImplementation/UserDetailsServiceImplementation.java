package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mvc.mysql.entity.UserDetailsEntity;
import com.mvc.mysql.entity.UserEntity;
import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.model.UserDetailsVM;
import com.mvc.mysql.repo.UserDetailsRepository;
import com.mvc.mysql.service.UserDetailsService;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	ModelMapper mm;
	
	@Override
	public List<UserDetailsMV> getAllCustomer() {
		// TODO Auto-generated method stub
		System.out.println("Get all Employees...");

		List<UserDetailsEntity> customers = new ArrayList<>();

		userDetailsRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<UserDetailsEntity>>() {
		}.getType();
		return mm.map(customers, listType);
	}

	@Override
	public UserDetailsMV postCustomer(UserDetailsVM customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteCustomer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDetailsMV> updateCustomer(long id, UserDetailsVM customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
