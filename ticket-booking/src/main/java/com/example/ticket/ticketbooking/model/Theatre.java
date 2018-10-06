package com.example.ticket.ticketbooking.model;

import java.util.List;

public class Theatre {

	private Integer id;
	private String name;
	private List<MovieDTO> movies;
	private AddressDTO addressDTO;

	public AddressDTO getAddress() {
		return addressDTO;
	}

	public List<MovieDTO> getMovies() {
		return movies;
	}

	public String getName() {
		return name;
	}

	public void setAddress(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Theatre [name=" + name + ", movies=" + movies + ", address=" + addressDTO + "]";
	}

}
