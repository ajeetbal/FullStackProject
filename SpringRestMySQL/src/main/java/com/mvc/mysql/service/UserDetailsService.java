package com.mvc.mysql.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mvc.mysql.model.UserDetailsMV;
import com.mvc.mysql.model.UserDetailsVM;

public interface UserDetailsService {

	List<UserDetailsMV> getAllCustomer();

	UserDetailsMV postCustomer(UserDetailsVM customer);

	ResponseEntity<String> deleteCustomer(long id);

	ResponseEntity<UserDetailsMV> updateCustomer(long id, UserDetailsVM customer);

}
