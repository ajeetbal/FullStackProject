package com.mvc.mysql.model;

import java.util.Set;

public class DistributorVM {

	
	private String password;
	private String name;
	 
	private Set<InventoryVM> inventoryProduct;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the inventoryProduct
	 */
	public Set<InventoryVM> getInventoryProduct() {
		return inventoryProduct;
	}

	/**
	 * @param inventoryProduct the inventoryProduct to set
	 */
	public void setInventoryProduct(Set<InventoryVM> inventoryProduct) {
		this.inventoryProduct = inventoryProduct;
	}

}
