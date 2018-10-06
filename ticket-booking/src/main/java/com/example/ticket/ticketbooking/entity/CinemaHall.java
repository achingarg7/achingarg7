package com.example.ticket.ticketbooking.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_hall")
public class CinemaHall implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cinema_hall_id")
	private Integer id;

	@Column(name = "cinema_hall_name")
	private String cinemaHallName;

	@OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL)
	private List<Movie> movieNames;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "cinemaHall")
	private Location location;

	public CinemaHall() {

	}

	public CinemaHall(Integer id, String cinemaHallName, List<Movie> movieNames, Location location) {
		super();
		this.id = id;
		this.cinemaHallName = cinemaHallName;
		this.movieNames = movieNames;
		this.location = location;
	}

	public String getCinemaHallName() {
		return cinemaHallName;
	}

	public Integer getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public List<Movie> getMovieNames() {
		return movieNames;
	}

	public void setCinemaHallName(String cinemaHallName) {
		this.cinemaHallName = cinemaHallName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setMovieNames(List<Movie> movieNames) {
		this.movieNames = movieNames;
	}

	@Override
	public String toString() {
		return "CinemaHall [id=" + id + ", cinemaHallName=" + cinemaHallName + ", movieNames=" + movieNames
				+ ", location=" + location + "]";
	}

}
