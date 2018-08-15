package com.achin.ticketbooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.achin.ticketbooking.model.Movie;
import com.achin.ticketbooking.model.Ticket;
import com.achin.ticketbooking.repository.MovieRepository;
import com.achin.ticketbooking.repository.TicketRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movie")
@Api(value = "movie", description = "Rest API for movie operations", tags = "Movie API")
public class TicketController {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	TicketRepository ticketRepository;

	@RequestMapping(value="/addmovie", method=RequestMethod.POST)
	@ApiOperation(value = "Add a Movie to the list", response = Movie.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
	public Movie addMovie(@RequestBody Movie movie) {

		Movie savedMovie = movieRepository.save(movie);
		
		return savedMovie;

	}

	@RequestMapping(value = "/getmovies", method = RequestMethod.GET)
	@ApiOperation(value = "Display list of all movies", response = Movie.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found")
    }
    )
	public List<Movie> getAllMovies() {

		return movieRepository.findAll();
	}
	
	@PostMapping(value="/bookTicket/{movie}/{seats}")
	public Ticket bookTicket(@PathVariable String movie, @PathVariable List<Integer> seats){
		System.out.println("Seats Requestd:"+seats);
		
		if(getSeatsFromCache(movie)==null){
			addSeatsToCache(movie,seats);
		}else{
			List<Integer> listOfSeats = getSeatsFromCache(movie);
			
			System.out.println("Seats In Cache:"+listOfSeats.toString());
			for(Integer seat : seats){
				for(Integer s: listOfSeats){
					if(seat==s){
						System.out.println("Seat Locked:"+seat);
						break;
					}
				}
			}
		}
				
		return null;
	}
	
    @CachePut(value = "ticket", key="#movieName")
    public List<Integer> addSeatsToCache(String movieName, List<Integer> seats) {
    //save is not required since @CachePut takes care to put in cache, save is use for DB.
      //repository.save(book);
      return seats;
    }
    
    @Cacheable(value = "ticket", key = "#movieName")
    public List<Integer> getSeatsFromCache(String movieName) 
    {
        //Book insertedBook = repository.findById(id).get();
        return null;
    }
}