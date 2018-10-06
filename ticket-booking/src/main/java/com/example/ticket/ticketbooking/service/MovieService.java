package com.example.ticket.ticketbooking.service;

import org.springframework.http.ResponseEntity;

import com.example.ticket.ticketbooking.model.CustomDTO;
import com.example.ticket.ticketbooking.model.Theatre;

public interface MovieService {

	ResponseEntity<CustomDTO> getAllTheatres();

	ResponseEntity<CustomDTO> getTheatre(Integer id);

	ResponseEntity<CustomDTO> addTheatre(Theatre theatre);

	ResponseEntity<CustomDTO> updateTheatre(Theatre theatre);

	ResponseEntity<CustomDTO> deleteTheatre(Integer id);

	ResponseEntity<CustomDTO> addMoviestoCinemaHall(Theatre theatre);

	ResponseEntity<CustomDTO> deleteMovieFromCinemaHall(Integer theatreId, Integer movieId);

	ResponseEntity<CustomDTO> deleteMoviesFromCinemaHall(Theatre theatre);

	ResponseEntity<CustomDTO> getTheatresByMovieName(String movieName);

	ResponseEntity<CustomDTO> getAllMovies();
}