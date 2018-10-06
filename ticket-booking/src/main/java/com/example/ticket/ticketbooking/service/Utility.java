package com.example.ticket.ticketbooking.service;

import org.springframework.stereotype.Service;

import com.example.ticket.ticketbooking.entity.CinemaHall;

@Service
public class Utility {

	public CinemaHall generateCinemaHall(CinemaHall old, CinemaHall recent) {
		if (recent.getCinemaHallName() != null) {
			old.setCinemaHallName(recent.getCinemaHallName());
		}
		if (recent.getLocation() != null) {
			if (recent.getLocation().getArea() != null) {
				old.getLocation().setArea(recent.getLocation().getArea());
			}
			if (recent.getLocation().getCity() != null) {
				old.getLocation().setCity(recent.getLocation().getCity());
			}
			if (recent.getLocation().getPincode() != null) {
				old.getLocation().setPincode(recent.getLocation().getPincode());
			}

			if (recent.getLocation().getState() != null) {
				old.getLocation().setState(recent.getLocation().getState());
			}
			if (recent.getLocation().getStreet() != null) {
				old.getLocation().setStreet(recent.getLocation().getStreet());
			}
		}
		return old;
	}
}
