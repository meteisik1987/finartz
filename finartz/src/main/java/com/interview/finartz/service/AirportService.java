package com.interview.finartz.service;

import java.util.List;

import com.interview.finartz.entity.Airport;

public interface AirportService {

	public List<Airport> findAllAirports();
	
	public Airport getAirportById(int id);

	public Airport createAirport(Airport airport);

	public void deleteAirport(int id);

	public Airport updateAirport(Airport oldAirport, Airport airportParam);

	public List<Airport> searchAirportByName(String name);

}
