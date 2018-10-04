package com.mvc.mysql.service;

import java.util.List;

import com.mvc.mysql.model.UserDetailsMV;

public interface UserDetailsService {

	List<UserDetailsMV> getAllCustomer();

	UserDetailsMV add(Long id);

}
