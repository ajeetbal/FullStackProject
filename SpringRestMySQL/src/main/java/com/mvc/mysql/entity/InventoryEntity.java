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
@Table(name = "inventory")
public class InventoryEntity {


	    private long id;
	    private String productName;
	    private String productDescription;    
	    private DistributorEntity employeeCategory;
	    @Column(name="status")
	    private boolean status;
	    
	    @Column(name="price")
	    private int price;
	    
	    @Column(name="quantity")
	    private int quantity;
	    
	    
	    /**
		 * @return the status
		 */
		public boolean getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(boolean status) {
			this.status = status;
		}

		/**
		 * @return the price
		 */
		public int getPrice() {
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(int price) {
			this.price = price;
		}

		

		/**
		 * @return the quantity
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public InventoryEntity() {

	    }


	    public InventoryEntity(String productDescription,String productName,boolean status,int price,int quantity, DistributorEntity employeeCategory) {
	        this.productDescription=productDescription;
	        this.employeeCategory = employeeCategory;
	        this.status=status;
	        this.price=price;
	        this.quantity=quantity;
	        this.productName=productName;
	    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    
	    
        @NotNull
	    @ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	    @JoinColumn(name = "distributor_id" ,referencedColumnName="id")
	    public DistributorEntity getemployeeCategory() {
	        return employeeCategory;
	    }

	    public void setemployeeCategory(DistributorEntity employeeCategory) {
	        this.employeeCategory = employeeCategory;
	    }

		/**
		 * @return the productDescription
		 */
		public String getProductDescription() {
			return productDescription;
		}

		/**
		 * @param productDescription the productDescription to set
		 */
		public void setProductDescription(String productDescription) {
			this.productDescription = productDescription;
		}

		/**
		 * @return the productName
		 */
		public String getProductName() {
			return productName;
		}

		/**
		 * @param productName the productName to set
		 */
		public void setProductName(String productName) {
			this.productName = productName;
		}	

}
