package com.mvc.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distributor")
public class DistributorEntity {

		private long id;
		
	    private String name;
	   
	    
	    
	    
	    
	    @Column(name="password")
	    private String password;
	    public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public DistributorEntity() {
			// TODO Auto-generated constructor stub
		}
	   

	    
		public DistributorEntity(long id, String name, String password) {
			super();
			this.id = id;
			this.name = name;
			
			this.password = password;
		}

		@Id
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

		
		

		
}

