package com.mvc.mysql.entity;

import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "inventory")
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "productName")
	private String productName;

	@NotNull
	@Column(name = "productDescription")
	private String productDescription;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "distributor_id", referencedColumnName = "id")
	private DistributorEntity distributor;

	@NotNull
	@Column(name = "status")
	private boolean status;
    
	@NotNull
	@Column(name = "price")
	private int price;

	@NotNull
	@Column(name = "quantity")
	private int quantity;

	
	@Column(name = "createdOn")
	@CreationTimestamp
	private Date createdOn;

	@Column(name = "updatedOn")
	@UpdateTimestamp
	private Date updatedOn;

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
	 * @return createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * 
	 * @param createdOn
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 
	 * @return updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 
	 * @param updatedOn
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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
	 */
	public InventoryEntity() {

	}

/**
 * 
 * @return id
 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return distributor
	 */
	public DistributorEntity getemployeeCategory() {
		return distributor;
	}

	/**
	 * 
	 * @param employeeCategory
	 */
	public void setemployeeCategory(DistributorEntity employeeCategory) {
		this.distributor = employeeCategory;
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
	 * 
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 
	 * @param id
	 * @param productName
	 * @param productDescription
	 * @param distributor
	 * @param status
	 * @param price
	 * @param quantity
	 * @param createdOn
	 * @param updatedOn
	 */
	
	public InventoryEntity(Long id, @NotNull String productName, @NotNull String productDescription,
			@NotNull DistributorEntity distributor, @NotNull boolean status, @NotNull int price, @NotNull int quantity,
			Date createdOn, Date updatedOn) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.distributor = distributor;
		this.status = status;
		this.price = price;
		this.quantity = quantity;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "InventoryEntity [id=" + id + ", productName=" + productName + ", productDescription="
				+ productDescription + ", employeeCategory=" + distributor + ", status=" + status + ", price=" + price
				+ ", quantity=" + quantity + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

}
