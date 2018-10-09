package com.mvc.mysql.model;

import java.util.Set;

public class UserMV {


	private long id;
	private String password;
	private String name;
	private String username;
    private Set<UserDetailsMV> education;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the education
	 */
	public Set<UserDetailsMV> getEducation() {
		return education;
	}
	/**
	 * @param education the education to set
	 */
	public void setEducation(Set<UserDetailsMV> education) {
		this.education = education;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
