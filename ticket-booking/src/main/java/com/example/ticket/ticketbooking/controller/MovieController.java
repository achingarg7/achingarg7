package com.example.ticket.ticketbooking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket.ticketbooking.model.CustomDTO;
import com.example.ticket.ticketbooking.model.Theatre;
import com.example.ticket.ticketbooking.service.MovieService;

@RestController
@RequestMapping("/booking/v1")
public class MovieController {

	@Autowired
	MovieService movieService;

	@RequestMapping(path = "/theatres", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomDTO> getAllTheatres() {
		return movieService.getAllTheatres();
	}

	@RequestMapping(path = "/theatre/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomDTO> getTheatre(@PathVariable Integer id) {
		return movieService.getTheatre(id);
	}

	@RequestMapping(path = "/theatre", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomDTO> addTheatre(@Valid @RequestBody Theatre theatre) {
		return movieService.addTheatre(theatre);
	}

	@RequestMapping(path = "/theatre", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomDTO> updateTheatre(@Valid @RequestBody Theatre theatre) {
		return movieService.updateTheatre(theatre);
	}

	@RequestMapping(path = "/theatre/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<CustomDTO> deleteTheatre(@PathVariable Integer id) {
		return movieService.deleteTheatre(id);

	}

	@RequestMapping(path = "/theatre/movies", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomDTO> addMoviesToTheatre(@Valid @RequestBody Theatre theatre) {
		return movieService.addMoviestoCinemaHall(theatre);
	}

	@RequestMapping(path = "/theatre/movie/{theatreId}/{movieId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<CustomDTO> deleteMovieFromTheatre(@PathVariable Integer theatreId,
			@PathVariable Integer movieId) {
		return movieService.deleteMovieFromCinemaHall(theatreId, movieId);
	}

	@RequestMapping(path = "/theatre/movies", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<CustomDTO> deleteMoviesFromTheatre(@Valid @RequestBody Theatre theatre) {
		return movieService.deleteMoviesFromCinemaHall(theatre);
	}

	@RequestMapping(path = "/theatres/{movieName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomDTO> getTheatresByMovieName(@PathVariable String movieName) {
		return movieService.getTheatresByMovieName(movieName);
	}

	@RequestMapping(path = "/movies", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomDTO> getAllMovies() {
		return movieService.getAllMovies();
	}
}
