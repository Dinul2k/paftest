package net.javaguides.usermanagement.dao;

public class Customer {

	private int id;
	private String name;
	private String address;
	private String unitsconsumed;
	private String billamount;
	private String date;
	
	
	
	public Customer(int id, String name, String address, String unitsconsumed, String billamount, String date) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.unitsconsumed = unitsconsumed;
		this.billamount = billamount;
		this.date = date;
	}
	
	
	
	public Customer(String name, String address, String unitsconsumed, String billamount, String date) {
		super();
		this.name = name;
		this.address = address;
		this.unitsconsumed = unitsconsumed;
		this.billamount = billamount;
		this.date = date;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUnitsconsumed() {
		return unitsconsumed;
	}
	public void setUnitsconsumed(String unitsconsumed) {
		this.unitsconsumed = unitsconsumed;
	}
	public String getBillamount() {
		return billamount;
	}
	public void setBillamount(String billamount) {
		this.billamount = billamount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
