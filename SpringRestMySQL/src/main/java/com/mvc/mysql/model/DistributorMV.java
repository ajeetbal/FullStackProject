package com.mvc.mysql.model;

import java.util.List;
import java.util.Set;

public class DistributorMV {

	private long id;
	private String password;
	private String name;
    private Set<InventoryMV> education;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<InventoryMV> getEducation() {
		return education;
	}
	public void setEducation(Set<InventoryMV> education) {
		this.education = education;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
