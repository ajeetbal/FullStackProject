package com.mvc.mysql.model;

import java.util.Set;

public class UserVM {

	private long id;
	private String password;
	private String name;
	private Set<InventoryVM> education;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<InventoryVM> getEducation() {
		return education;
	}

	public void setEducation(Set<InventoryVM> education) {
		this.education = education;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
