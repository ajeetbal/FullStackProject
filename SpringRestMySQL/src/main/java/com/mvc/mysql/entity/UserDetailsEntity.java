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

/**
 * @author ajeet.bal
 *
 */
@Entity
@Table(name = "userDetails")
public class UserDetailsEntity {

	private long id;

	
	@NotNull
	@Column(name = "productName")
	private String productName;

	@NotNull
	@Column(name = "productDescription")
	private String productDescription;
	
	@NotNull
	private UserEntity employeeCategory;
	
	@NotNull
	@Column(name = "status")
	private boolean status;

	@NotNull
	@Column(name = "price")
	private int price;

	@NotNull
	@Column(name = "quantity")
	private int quantity;
	
	@NotNull
	@Column(name="total")
	private int total;

	/**
	 * 
	 * @return status
	 */
	public boolean getStatus() {
		return status;
	}
	/**
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    /**
     * 
     * @return id
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 
	 * @param prodctName
	 */
	public void setProductName(String prodctName) {
		this.productName = prodctName;
	}

	/**
	 * 
	 * @return employeeCategory
	 */
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	public UserEntity getemployeeCategory() {
		return employeeCategory;
	}

	/**
	 * 
	 * @param employeeCategory
	 */
	public void setemployeeCategory(UserEntity employeeCategory) {
		this.employeeCategory = employeeCategory;
	}
	/**
	 * 
	 * @return productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * 
	 * @param productDescription
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	public UserDetailsEntity() {

	}
	/**
	 * 
	 * @param id
	 * @param productName
	 * @param productDescription
	 * @param employeeCategory
	 * @param status
	 * @param price
	 * @param quantity
	 * @param total
	 */
	public UserDetailsEntity(long id, @NotNull String productName, @NotNull String productDescription,
			@NotNull UserEntity employeeCategory, @NotNull boolean status, @NotNull int price, @NotNull int quantity,
			@NotNull int total) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.employeeCategory = employeeCategory;
		this.status = status;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}


}
