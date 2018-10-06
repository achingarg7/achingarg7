package com.example.ticket.ticketbooking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ticket.ticketbooking.entity.CinemaHall;
import com.example.ticket.ticketbooking.entity.Movie;

@Repository
public interface BookingRepository extends JpaRepository<CinemaHall, Integer> {

	@Modifying
	@Transactional
	@Query(value = "delete from Movie m where m.id=?1")
	public int deleteMovieFromCinemaHall(Integer movieId);

	@Modifying
	@Transactional
	@Query(value = "delete from Movie m where m.id in (?1)")
	public int deleteMoviesFromCinemaHall(List<Integer> ids);

	public List<CinemaHall> findByMovieNamesMovieNameContaining(String movieName);

	@Query(value = "select m from Movie m")
	public List<Movie> findAllMovies();

}
