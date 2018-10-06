package com.example.ticket.ticketbooking.model;

public class AddressDTO {

	private Integer id;
	private String state;
	private String city;
	private String area;
	private String street;
	private Long pincode;

	public String getArea() {
		return area;
	}

	public String getCity() {
		return city;
	}

	public Long getPincode() {
		return pincode;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Address [state=" + state + ", city=" + city + ", area=" + area + ", street=" + street + ", pincode="
				+ pincode + "]";
	}

}
