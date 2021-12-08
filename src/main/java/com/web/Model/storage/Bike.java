package com.web.Model.storage;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bike")
public class Bike {

	private long id;
	private String name;
	private String model;
	private String company;
	private String checisNumber;
	private String engineNumber;
	private String purchaseDate;
	
	
	public Bike(long id, String name, String model, String company, String checisNumber, String engineNumber, String purchaseDate) {
		super();
		this.id = id;
		this.name = name;
		this.model = model;
		this.company = company;
		this.checisNumber = checisNumber;
		this.engineNumber = engineNumber;
		this.purchaseDate = purchaseDate;
	}
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getChecisNumber() {
		return checisNumber;
	}
	public void setChecisNumber(String checisNumber) {
		this.checisNumber = checisNumber;
	}
	public String getEngineNumber() {
		return engineNumber;
	}	
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
