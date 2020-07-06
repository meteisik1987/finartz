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

import com.interview.finartz.dao.FlightRepository;
import com.interview.finartz.dao.RouteRepository;
import com.interview.finartz.entity.Airline;
import com.interview.finartz.entity.Flight;
import com.interview.finartz.entity.Route;
import com.interview.finartz.service.AirlineService;
import com.interview.finartz.service.FlightService;

@RestController
public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	AirlineService airlineService;

	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	
	@GetMapping("/flights")
	public List<Flight> getFlights() {
		try {
			return flightService.findAllFlights();
		}catch (Exception e) {
			return new ArrayList<Flight>();
		}
	}

	@PostMapping(value = "/addFlight", produces = "application/json;charset=UTF-8")
	public ResponseEntity createFlight(@RequestBody Flight flight) {
		try {
			
			Airline airline = airlineService.getAirlineById(flight.getAirline().getId());
			
			if(airline==null || airline.getId() == 0) {
				return new ResponseEntity("Airline can not be found", HttpStatus.NOT_FOUND);
			}
			
			Route route = routeRepository.getOne(flight.getRoute().getId());
			
			if(route==null || route.getId() == 0) {
				return new ResponseEntity("Route can not be found", HttpStatus.NOT_FOUND);
			}

			
			flight.setAirline(airline);
			flight.setRoute(route);
			
			flightService.createFlight(flight);
			return new ResponseEntity("Success", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteFlight/{id}")
	public ResponseEntity deleteFlight(@PathVariable int id) {
		try {
			flightService.deleteFlight(id);
			return new ResponseEntity("Success", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/findRouteByFlightCode/{name}")
	public ResponseEntity findRouteByFlightCode(@PathVariable String name) {
		try {
			return new ResponseEntity(flightRepository.findRouteByFlightCode(name), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
}
