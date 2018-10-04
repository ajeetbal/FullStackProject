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

	@Column(name = "productName")
	private String productName;

	@Column(name = "productDescription")
	private String productDescription;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "distributor_id", referencedColumnName = "id")
	private DistributorEntity distributor;

	@Column(name = "status")
	private boolean status;

	@Column(name = "price")
	private int price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "createdOn")
	@CreationTimestamp
	private Date createdOn;

	@Column(name = "updatedOn")
	@UpdateTimestamp
	private Date updatedOn;

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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public InventoryEntity() {

	}

	public InventoryEntity(String productName, String productDescription, DistributorEntity employeeCategory,
			boolean status, int price, int quantity, Date createdOn, Date updatedOn) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.distributor = employeeCategory;
		this.status = status;
		this.price = price;
		this.quantity = quantity;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DistributorEntity getemployeeCategory() {
		return distributor;
	}

	public void setemployeeCategory(DistributorEntity employeeCategory) {
		this.distributor = employeeCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "InventoryEntity [id=" + id + ", productName=" + productName + ", productDescription="
				+ productDescription + ", employeeCategory=" + distributor + ", status=" + status + ", price=" + price
				+ ", quantity=" + quantity + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

}
