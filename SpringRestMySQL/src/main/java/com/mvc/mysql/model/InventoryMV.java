package com.mvc.mysql.model;

public class InventoryMV {
	 private long id;
	    private String productName;
	    private String productDescription;
	    private boolean status;
	    private int price;
	    private int quantity;

	   // private EmployeeEntity employeeCategory;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		/**
		 * @return the employeeCategory
		 */
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
	
		
		

}
