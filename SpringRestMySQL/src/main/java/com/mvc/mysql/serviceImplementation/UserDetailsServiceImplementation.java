package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mysql.entity.InventoryEntity;
import com.mvc.mysql.entity.UserDetailsEntity;
import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.repo.InventoryRepository;
import com.mvc.mysql.repo.UserDetailsRepository;
import com.mvc.mysql.repo.UserRepository;
import com.mvc.mysql.service.InventoryService;
import com.mvc.mysql.service.UserDetailsService;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	ModelMapper modelMapper;
	private static Logger log = Logger.getLogger(UserDetailsServiceImplementation.class);

	@Override
	public List<UserDetailsMV> getAllCustomer() {
		// TODO Auto-generated method stub

		List<UserDetailsEntity> customers = new ArrayList<>();

		userDetailsRepository.findAll().forEach(customers::add);
		Type listType = new TypeToken<List<UserDetailsEntity>>() {
		}.getType();
		return modelMapper.map(customers, listType);
	}

	@Override
	public UserDetailsMV add(Long id) {
		// TODO Auto-generated method stub

		Optional<InventoryEntity> inventory = inventoryRepository.findById(id);
		System.out
				.println("=================================================" + inventory.get().getProductDescription());
		log.info(inventory.get().toString());
		log.info("entered");

		UserDetailsEntity customers = userDetailsRepository
				.save(modelMapper.map(inventory.get(), UserDetailsEntity.class));
//		log.info("second");customers 

		return modelMapper.map(customers, UserDetailsMV.class);

	}
}