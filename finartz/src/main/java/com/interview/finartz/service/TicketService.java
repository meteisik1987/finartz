package com.interview.finartz.service;

import java.util.List;

import com.interview.finartz.entity.Flight;
import com.interview.finartz.entity.Ticket;

public interface TicketService {

	public List<Ticket> findAllTickets();

	public Ticket buyTicket(Flight flight, Ticket ticket);

	public void deleteTicket(int id);

	public void deleteAllTickets();
	
	public Ticket findTicketById(int id);

}
