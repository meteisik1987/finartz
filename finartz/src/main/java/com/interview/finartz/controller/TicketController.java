package com.interview.finartz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.finartz.entity.Flight;
import com.interview.finartz.entity.Ticket;
import com.interview.finartz.service.FlightService;
import com.interview.finartz.service.TicketService;

@RestController
public class TicketController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private TicketService ticketService;

	@GetMapping("/tickets")
	public List<Ticket> getTickets() {
		try {
			List<Ticket> ticketList = ticketService.findAllTickets();

			for (Ticket ticketParam : ticketList) {
				String creditCard = ticketParam.getCreditCard();
				ticketParam.setCreditCard(maskString(creditCard, 5, 14, '*'));
			}
			return ticketList;
		} catch (Exception e) {
			return new ArrayList<Ticket>();
		}
	}

	@PostMapping(value = "/buyTicket", produces = "application/json;charset=UTF-8")
	public ResponseEntity buyTicket(@RequestBody Ticket ticket) {
		try {
			if (ticket.getFlight() == null || ticket.getFlight().getId() == 0) {
				return new ResponseEntity("Flight can not be null or empty", HttpStatus.NOT_FOUND);
			}

			if (ticket.getCreditCard() == null || ticket.getCreditCard().length() == 0) {
				return new ResponseEntity("Credit Card can not be null or empty", HttpStatus.NOT_FOUND);
			}

			// Accept all kinds of credit card.
			ticket.setCreditCard(changeCreditCard(ticket.getCreditCard()));
			
			Flight flight = flightService.getFlightById(ticket.getFlight().getId());

			if (flight == null || flight.getId() == 0) {
				return new ResponseEntity("Flight can not be found", HttpStatus.NOT_FOUND);
			}

			ticketService.buyTicket(flight, ticket);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteTicket/{id}")
	public ResponseEntity deleteTicket(@PathVariable int id) {
		try {
			ticketService.deleteTicket(id);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteAllTickets")
	public ResponseEntity deleteTicket() {
		try {
			ticketService.deleteAllTickets();
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/findTicketById/id")
	public ResponseEntity findTicketById(@PathVariable int id) {
		try {
			return new ResponseEntity(ticketService.findTicketById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	private static String maskString(String strText, int start, int end, char maskChar) throws Exception {

		if (strText == null || strText.equals("")) {
			return "";
		}

		if (start < 0) {
			start = 0;
		}

		if (end > strText.length()) {
			end = strText.length();
		}

		if (start > end) {
			throw new Exception("End index cannot be greater than start index");
		}

		int maskLength = end - start;

		if (maskLength == 0) {
			return strText;
		}

		StringBuilder sbMaskString = new StringBuilder(maskLength);

		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}

		return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
	}
	
	public String changeCreditCard(String creditCard) {
		creditCard = creditCard.replaceAll("-", "");
		creditCard = creditCard.replaceAll("/", "");
		creditCard = creditCard.replaceAll(",", "");
		return creditCard;
	}

}
