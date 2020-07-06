package com.interview.finartz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.finartz.dao.TicketRepository;
import com.interview.finartz.entity.Flight;
import com.interview.finartz.entity.Ticket;
import com.interview.finartz.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	private static final int quota = 10;
	private static final double percentage = 10.0;

	@Override
	public List<Ticket> findAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket buyTicket(Flight flight, Ticket ticket) {
		ticket.setFlight(flight);

		int fullSeat = ticketRepository.getTicketCountByFlightId(flight.getId());
		int perc = (int) (fullSeat * 100 / flight.getCapacity() / quota);

		double newPrice = flight.getPrice() + flight.getPrice() * perc * percentage / 100;
		ticket.setCharge(newPrice);

		return ticketRepository.save(ticket);
	}

	@Override
	public void deleteTicket(int id) {
		ticketRepository.deleteById(id);
	}

	@Override
	public void deleteAllTickets() {
		ticketRepository.deleteAll();
	}

	@Override
	public Ticket findTicketById(int id) {
		return ticketRepository.getOne(id);
	}

	public static String maskCardNumber(String cardNumber, String mask) {

		// format the number
		int index = 0;
		StringBuilder maskedNumber = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			char c = mask.charAt(i);
			if (c == '#') {
				maskedNumber.append(cardNumber.charAt(index));
				index++;
			} else if (c == 'x') {
				maskedNumber.append(c);
				index++;
			} else {
				maskedNumber.append(c);
			}
		}
		return maskedNumber.toString();
	}

}
