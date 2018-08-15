package com.achin.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achin.ticketbooking.model.Movie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
