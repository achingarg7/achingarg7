package com.achin.ticketbooking.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ticket")
public class Ticket {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="booked_date")
	private Timestamp bookedDate;
	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="seat_num")
	private Integer seatNum;

	public Ticket(Integer id, Timestamp bookedDate, String movieName, Integer seatNum) {
		super();
		this.id = id;
		this.bookedDate = bookedDate;
		this.movieName = movieName;
		this.seatNum = seatNum;
	}

	public Ticket() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Timestamp bookedDate) {
		this.bookedDate = bookedDate;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	
	
}
