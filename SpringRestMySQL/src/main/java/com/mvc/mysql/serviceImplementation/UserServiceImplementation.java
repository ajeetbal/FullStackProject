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

	private String password;

	@Autowired
	ModelMapper modelMapper;

	String encrypted;

	/**
	 * @description Get all users
	 */
	@Override
	public List<UserMV> getAllUsers() throws InternalServerException, ResourceNotFound {
		logger.info("get all users");
		try {
			List<UserEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		Type listType = new TypeToken<List<UserEntity>>() {
		}.getType();
		
			if (users.isEmpty()) {
				throw new ResourceNotFound("User not found");
			}
			return modelMapper.map(users, listType);
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");
		}
	}

	/**
	 * @description Create users
	 */
	@Override
	public ResponseEntity<UserMV> postUsers(UserVM users) throws BadRequestException, InternalServerException {
		// TODO Auto-generated method stub
		try {
			logger.info("Create users");
			if (users == null) {
				throw new BadRequestException("You can't send null in fields..");
			} else {
				UserEntity c = modelMapper.map(users, UserEntity.class);
				String encrypted = encrypt(users.getPassword());
				c.setPassword(encrypted);
				UserEntity _user = userRepository.save(c);
				return new ResponseEntity<UserMV>(modelMapper.map(_user, UserMV.class), HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error ");
		}
	}

	/**
	 * @description Delete users by id
	 */
	@Override
	public ResponseEntity<String> deleteUsers(long id) throws ResourceNotFound {

		logger.info("delete user having" + id);
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);

			return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
		} else {
			throw new ResourceNotFound("user id not found..");
		}
	}

	/**
	 * @description update users
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<UserMV> updateUsers(long id, UserVM user)
			throws BadRequestException, InternalServerException {
		// TODO Auto-generated method stub
		logger.info("Update users");
		try {
			if (user == null) {
				throw new BadRequestException("You can't send null in fields..");
			}
			Optional<UserEntity> userData = userRepository.findById(id);
			if (userData.isPresent()) {
				UserEntity _user = userData.get();
				_user.setName(user.getName());
				

				return new ResponseEntity<UserMV>((MultiValueMap<String, String>) userRepository.save(_user),
						HttpStatus.OK);
			} else {
				throw new ResourceNotFound("user not found");
			}
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");
		}
	}

	/**
	 * @description Login users
	 */
	@Override
	public ResponseEntity<String> loginUsers(UserVM users) throws BadRequestException, InternalServerException {
		logger.info("LogIn users");
		try {
			if (users == null) {
				throw new BadRequestException("You can't send null in fields..");

			}
			List<UserEntity> userData = userRepository.findByNameAndPassword(users.getName(), encrypt(users.getPassword()));
			if (userData.isEmpty()) {
				return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>("successfully loged in!", HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new InternalServerException("Internal Server Error");
		}
	}

	/**
	 * @description encrypt password
	 */
	public String encrypt(String password) {

		this.password = password;

		return "crd56" + this.password + "!@#awfs88";
	}

}
