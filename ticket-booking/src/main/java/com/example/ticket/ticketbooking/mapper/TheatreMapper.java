package com.example.ticket.ticketbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import com.example.ticket.ticketbooking.entity.CinemaHall;
import com.example.ticket.ticketbooking.entity.Location;
import com.example.ticket.ticketbooking.entity.Movie;
import com.example.ticket.ticketbooking.model.AddressDTO;
import com.example.ticket.ticketbooking.model.MovieDTO;
import com.example.ticket.ticketbooking.model.Theatre;

@Mapper(componentModel = "spring", nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS)
public interface TheatreMapper {

	@Mappings({ @Mapping(source = "address.state", target = "state"),
			@Mapping(source = "address.city", target = "city"), @Mapping(source = "address.area", target = "area"),
			@Mapping(source = "address.street", target = "street"),
			@Mapping(source = "address.pincode", target = "pincode"), @Mapping(source = "address.id", target = "id") })
	public Location addressToLocation(AddressDTO address);

	@Mappings({ @Mapping(source = "cinema.cinemaHallName", target = "name"),
			@Mapping(source = "cinema.movieNames", target = "movies"),
			@Mapping(source = "cinema.location", target = "address"), @Mapping(source = "cinema.id", target = "id") })
	public Theatre cinemaHallToTheatre(CinemaHall cinema);

	@Mappings({ @Mapping(source = "location.street", target = "street"),
			@Mapping(source = "location.area", target = "area"), @Mapping(source = "location.city", target = "city"),
			@Mapping(source = "location.state", target = "state"),
			@Mapping(source = "location.pincode", target = "pincode"),
			@Mapping(source = "location.id", target = "id") })
	public AddressDTO locationToAddress(Location location);

	@Mappings({ @Mapping(source = "theatre.name", target = "cinemaHallName"),
			@Mapping(source = "theatre.movies", target = "movieNames"),
			@Mapping(source = "theatre.address", target = "location"), @Mapping(source = "theatre.id", target = "id") })
	public CinemaHall theatreToCinemaHall(Theatre theatre);

	@Mappings({ @Mapping(source = "movie.id", target = "id"),
			@Mapping(source = "movie.movieName", target = "movieName"),
			@Mapping(source = "movie.releaseDate", target = "releaseDate") })
	public MovieDTO movieToMovieDTO(Movie movie);

	@Mappings({ @Mapping(source = "dto.id", target = "id"), @Mapping(source = "dto.movieName", target = "movieName"),
			@Mapping(source = "dto.releaseDate", target = "releaseDate") })
	public Movie movieDTOToMovie(MovieDTO dto);
}
