package com.interview.finartz.service;

import java.util.List;

import com.interview.finartz.entity.Airline;

public interface AirlineService {
	
	public List<Airline> findAllAirlines();
	
	public Airline getAirlineById(int id);
	
	public Airline createAirline(Airline airline);
	
	public void deleteAirline(int id);
	
	public Airline updateAirline(Airline oldAirline, Airline airlineParam);
	
	public List<Airline> searchAirlineByName(String name);
	
}
