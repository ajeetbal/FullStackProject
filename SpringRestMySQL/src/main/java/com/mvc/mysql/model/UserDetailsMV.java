package com.mvc.mysql.model;

public class UserDetailsMV {
	private long id;
	private String productName;
	private String productDescription;

	private int price;
	private int quantity;
	private int total;

	// private EmployeeEntity employeeCategory;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the prodctName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param prodctName the prodctName to set
	 */
	public void setProductName(String prodctName) {
		this.productName = prodctName;
	}

	/**
	 * @return the employeeCategory
	 */
	/**


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

}