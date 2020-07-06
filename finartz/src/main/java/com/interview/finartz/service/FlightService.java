package com.interview.finartz.service;

import java.util.List;

import com.interview.finartz.entity.Flight;

public interface FlightService {

	public List<Flight> findAllFlights();

	public Flight getFlightById(int id);

	public Flight createFlight(Flight flight);

	public void deleteFlight(int id);

	public Flight increasePriceByPercent(Flight flight);

	public Flight decreasePriceByPercent(Flight flight);

}
