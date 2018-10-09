package com.mvc.mysql.serviceImplementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mvc.mysql.entity.InventoryEntity;
import com.mvc.mysql.entity.UserDetailsEntity;
import com.mvc.mysql.exception.BadRequestException;
import com.mvc.mysql.exception.InternalServerException;
import com.mvc.mysql.exception.ResourceNotFound;
import com.mvc.mysql.model.InventoryMV;
import com.mvc.mysql.model.InventoryVM;
import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.model.UserDetailsVM;
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
	private static Logger logger = Logger.getLogger(UserDetailsServiceImplementation.class);

	/**
	 * @description get all User's product details
	 */
	@Override
	public List<UserDetailsMV> getAllUserDetails() throws InternalServerException, ResourceNotFound, BadRequestException{
		// TODO Auto-generated method stub
		try{
			logger.info("Get all User's product details");
		
		List<UserDetailsEntity> userDetails = new ArrayList<>();

		userDetailsRepository.findAll().forEach(userDetails::add);
		Type listType = new TypeToken<List<UserDetailsEntity>>() {
		}.getType();
		if(userDetails.isEmpty())
		{
			throw new ResourceNotFound("Details not found..");
		}
		return modelMapper.map(userDetails, listType);
	}
	catch(Exception e) {
		throw new InternalServerException("Internal Server Error");

	}
}
	/**
	 * @description add inventory to users product details
	 */
	@Override
	public UserDetailsMV add(Long id) {
		// TODO Auto-generated method stub
		logger.info("Add product details..");

		Optional<InventoryEntity> inventory = inventoryRepository.findById(id);
		
		
		
		UserDetailsEntity usersDetails = userDetailsRepository
				.save(modelMapper.map(inventory.get(), UserDetailsEntity.class));
		
		usersDetails.setTotal(usersDetails.getQuantity()*usersDetails.getPrice());
			
			 userDetailsRepository
				.save(modelMapper.map(inventory.get(), UserDetailsEntity.class));
		return modelMapper.map(usersDetails, UserDetailsMV.class);

	}
	}