package com.example.ticket.ticketbooking.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ticket.ticketbooking.entity.CinemaHall;
import com.example.ticket.ticketbooking.mapper.TheatreMapper;
import com.example.ticket.ticketbooking.model.CustomDTO;
import com.example.ticket.ticketbooking.model.MovieDTO;
import com.example.ticket.ticketbooking.model.Theatre;
import com.example.ticket.ticketbooking.repository.BookingRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	BookingRepository repo;
	@Autowired
	TheatreMapper mapper;
	@Autowired
	Utility util;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.ticket.ticketbooking.service.MovieService#getAllTheatres()
	 */
	@Override
	public ResponseEntity<CustomDTO> getAllTheatres() {
		List<Theatre> theatres = repo.findAll().stream().map(s -> mapper.cinemaHallToTheatre(s))
				.collect(Collectors.toList());
		CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
				theatres);
		return new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.ticket.ticketbooking.service.MovieService#getTheatre(java.lang.
	 * Integer)
	 */
	@Override
	public ResponseEntity<CustomDTO> getTheatre(Integer id) throws EntityNotFoundException {
		Optional<CinemaHall> hall = repo.findById(id);
		ResponseEntity<CustomDTO> res = null;
		if (hall.isPresent()) {
			Theatre theatre = mapper.cinemaHallToTheatre(hall.get());
			CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
					theatre);
			res = new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("No Theatre has found by Id: " + id);
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.ticket.ticketbooking.service.MovieService#addTheatre(com.example.
	 * ticket.ticketbooking.model.Theatre)
	 */
	@Override
	public ResponseEntity<CustomDTO> addTheatre(Theatre theatre) {
		CinemaHall savedHall = mapper.theatreToCinemaHall(theatre);
		savedHall.getMovieNames().stream().forEach(m -> {
			m.setCinemaHall(savedHall);
		});
		savedHall.getLocation().setCinemaHall(savedHall);
		theatre = mapper.cinemaHallToTheatre(repo.saveAndFlush(savedHall));
		CustomDTO dto = new CustomDTO(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), "SUCCESS",
				new Date(), theatre);
		return new ResponseEntity<CustomDTO>(dto, HttpStatus.CREATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.ticket.ticketbooking.service.MovieService#updateTheatre(com.
	 * example.ticket.ticketbooking.model.Theatre)
	 */
	@Override
	public ResponseEntity<CustomDTO> updateTheatre(Theatre theatre) throws EntityNotFoundException {
		Optional<CinemaHall> hall = repo.findById(theatre.getId());
		ResponseEntity<CustomDTO> res = null;
		if (hall.isPresent()) {
			// hall = util.generateCinemaHall(hall.get(),
			// mapper.theatreToCinemaHall(theatre));
			theatre = mapper.cinemaHallToTheatre(
					repo.saveAndFlush(util.generateCinemaHall(hall.get(), mapper.theatreToCinemaHall(theatre))));
			CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
					theatre);
			res = new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("No Theatre has found by Id: " + theatre.getId());
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.ticket.ticketbooking.service.MovieService#deleteTheatre(java.lang
	 * .Integer)
	 */
	@Override
	public ResponseEntity<CustomDTO> deleteTheatre(Integer id) throws EntityNotFoundException {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		} else {
			throw new EntityNotFoundException("No Theatre has found by Id: " + id);
		}
		CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
				"Theatre with Id: " + id + " has been deleted successfully");
		return new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.ticket.ticketbooking.service.MovieService#addMoviestoCinemaHall(
	 * com.example.ticket.ticketbooking.model.Theatre)
	 */
	@Override
	public ResponseEntity<CustomDTO> addMoviestoCinemaHall(Theatre theatre) throws EntityNotFoundException {
		Optional<CinemaHall> hall = repo.findById(theatre.getId());
		ResponseEntity<CustomDTO> res = null;
		if (hall.isPresent()) {
			theatre.getMovies().forEach(x -> {
				hall.get().getMovieNames().add(mapper.movieDTOToMovie(x));
			});
			hall.get().getMovieNames().forEach(x -> x.setCinemaHall(hall.get()));
			theatre = mapper.cinemaHallToTheatre(repo.saveAndFlush(hall.get()));
			CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
					theatre);
			res = new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("No Theatre has found by Id: " + theatre.getId());
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.ticket.ticketbooking.service.MovieService#
	 * deleteMoviesFromCinemaHall(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ResponseEntity<CustomDTO> deleteMovieFromCinemaHall(Integer theatreId, Integer movieId)
			throws EntityNotFoundException {
		ResponseEntity<CustomDTO> res = null;
		if (repo.existsById(theatreId)) {
			int n = repo.deleteMovieFromCinemaHall(movieId);
			if (n == 0) {
				throw new EntityNotFoundException("No Movie has found by Id: " + movieId);
			} else {
				CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS",
						new Date(), "Movie has deleted with Id: " + movieId);
				res = new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
			}
		} else {
			throw new EntityNotFoundException("No Theatre has found by Id: " + theatreId);
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.ticket.ticketbooking.service.MovieService#
	 * deleteMoviesFromCinemaHall(com.example.ticket.ticketbooking.model.Theatre)
	 */
	@Override
	public ResponseEntity<CustomDTO> deleteMoviesFromCinemaHall(Theatre theatre) throws EntityNotFoundException {
		ResponseEntity<CustomDTO> res = null;
		if (repo.existsById(theatre.getId())) {
			int n = repo.deleteMoviesFromCinemaHall(
					theatre.getMovies().stream().map(m -> m.getId()).collect(Collectors.toList()));
			if (n == 0) {
				throw new EntityNotFoundException("None of the movie has found");
			} else {
				CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS",
						new Date(), n + " Movie record has been deleted successfully");
				res = new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
			}
		} else {
			throw new EntityNotFoundException("No Theatre has found by Id: " + theatre.getId());
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.ticket.ticketbooking.service.MovieService#getTheatresByMovieName(
	 * java.lang.String)
	 */
	@Override
	public ResponseEntity<CustomDTO> getTheatresByMovieName(String movieName) throws EntityNotFoundException {
		List<CinemaHall> halls = repo.findByMovieNamesMovieNameContaining(movieName);
		ResponseEntity<CustomDTO> res = null;
		if (halls != null && !halls.isEmpty()) {
			List<Theatre> theatres = halls.stream().map(c -> mapper.cinemaHallToTheatre(c))
					.collect(Collectors.toList());
			CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
					theatres);
			res = new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("No Theatre record has found by movie name : " + movieName);
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.ticket.ticketbooking.service.MovieService#getAllMovies()
	 */
	@Override
	public ResponseEntity<CustomDTO> getAllMovies() {
		List<MovieDTO> movies = repo.findAllMovies().stream().map(m -> mapper.movieToMovieDTO(m))
				.collect(Collectors.toList());
		CustomDTO dto = new CustomDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS", new Date(),
				movies);
		return new ResponseEntity<CustomDTO>(dto, HttpStatus.OK);
	}

}
