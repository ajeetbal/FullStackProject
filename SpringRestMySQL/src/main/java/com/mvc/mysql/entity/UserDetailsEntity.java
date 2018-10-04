package com.mvc.mysql.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userDetails")
public class UserDetailsEntity {

	private long id;

	@Column(name = "productName")
	private String productName;

	@Column(name = "productDescription")
	private String productDescription;
	
	private UserEntity employeeCategory;
	
	@Column(name = "status")
	private boolean status;

	@Column(name = "price")
	private int price;

	@Column(name = "quantity")
	private int quantity;

	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String prodctName) {
		this.productName = prodctName;
	}

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	public UserEntity getemployeeCategory() {
		return employeeCategory;
	}

	public void setemployeeCategory(UserEntity employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public UserDetailsEntity() {

	}

	public UserDetailsEntity(String productName, String productDescription, boolean status, int price, int quantity,
			UserEntity employeeCategory) {
		this.productName = productName;
		this.employeeCategory = employeeCategory;
		this.status = status;
		this.price = price;
		this.quantity = quantity;
		this.setProductDescription(productDescription);
	}
}
