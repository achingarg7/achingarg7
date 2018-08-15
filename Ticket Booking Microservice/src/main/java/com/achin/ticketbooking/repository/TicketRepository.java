package com.achin.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achin.ticketbooking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
