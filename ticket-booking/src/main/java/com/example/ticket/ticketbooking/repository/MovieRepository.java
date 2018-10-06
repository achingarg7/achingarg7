package com.example.ticket.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticket.ticketbooking.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
