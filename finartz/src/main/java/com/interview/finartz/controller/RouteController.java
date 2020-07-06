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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.finartz.dao.RouteRepository;
import com.interview.finartz.entity.Route;

@RestController
public class RouteController {

	@Autowired
	private RouteRepository routeRepository;

	@GetMapping("/routes")
	public List<Route> getRoutes() {
		try {
			return routeRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<Route>();
		}
	}

	@PostMapping(value = "/addRoute", produces = "application/json;charset=UTF-8")
	public ResponseEntity createRoute(@RequestBody Route route) {
		try {
			routeRepository.save(route);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteRoute/{id}")
	public ResponseEntity deleteRoute(@PathVariable int id) {
		try {
			routeRepository.deleteById(id);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/searchRoute")
	public ResponseEntity searchAirport(@RequestParam(value = "departure", required = false, defaultValue = "0") int departure,
			@RequestParam(value = "arrival", required = false, defaultValue = "0") int arrival) {
		try {

			if (departure != 0 && arrival != 0) {
				return new ResponseEntity(routeRepository.findRoute(departure, arrival), HttpStatus.OK);
			} else if (arrival != 0) {
				return new ResponseEntity(routeRepository.findRouteByArrival(arrival), HttpStatus.OK);
			} else if (departure != 0) {
				return new ResponseEntity(routeRepository.findRouteByDeparture(departure), HttpStatus.OK);
			} else {
				return new ResponseEntity("Please enter an airport id", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/searchRouteByAirportName/{name}")
	public ResponseEntity searchAirport(@PathVariable String name) {
		try {
			return new ResponseEntity(routeRepository.findRouteByAirportName(name), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
