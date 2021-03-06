package com.mvc.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(name = "name")
	private String name;

	

	@NotNull
	@Column(name = "password")
	private String password;

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return id
	 */
	
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}



	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 */
	public UserEntity(long id, @NotNull String name, @NotNull String password) {
		super();
		this.id = id;
		this.name = name;
		
		this.password = password;
	}


}
