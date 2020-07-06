package com.interview.finartz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.finartz.dao.FlightRepository;
import com.interview.finartz.dao.RouteRepository;
import com.interview.finartz.entity.Airline;
import com.interview.finartz.entity.Flight;
import com.interview.finartz.entity.Route;
import com.interview.finartz.service.AirlineService;
import com.interview.finartz.service.FlightService;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;

	private final static int percentage = 10;

	@Override
	public List<Flight> findAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flight getFlightById(int id) {
		return flightRepository.getOne(id);
	}

	@Override
	public Flight createFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	@Override
	public void deleteFlight(int id) {
		flightRepository.deleteById(id);
	}

	@Override
	public Flight increasePriceByPercent(Flight flight) {
		double oldPrice = flight.getPrice();
		double newPrice = oldPrice + oldPrice * percentage / 100.0;
		flight.setPrice(newPrice);
		return flightRepository.save(flight);
	}

	@Override
	public Flight decreasePriceByPercent(Flight flight) {
		double oldPrice = flight.getPrice();
		double newPrice = oldPrice + oldPrice * percentage / 100.0;
		flight.setPrice(newPrice);
		return flightRepository.save(flight);
	}

}
