package com.interview.finartz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.finartz.entity.Airport;
import com.interview.finartz.service.AirportService;

@RestController
public class AirportController {

	@Autowired
	private AirportService airportService;

	@GetMapping(value = "/airports", produces = "application/json;charset=UTF-8")
	public List<Airport> getAirports() {
		try {
			return airportService.findAllAirports();
		} catch (Exception e) {
			return new ArrayList<Airport>();
		}
	}

	@PostMapping("/addAirport")
	public ResponseEntity createAirport(@RequestBody Airport airport) {
		try {
			airportService.createAirport(airport);
			return new ResponseEntity(airport, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteAirport/{id}")
	public ResponseEntity deleteAirport(@PathVariable int id) {
		try {
			airportService.deleteAirport(id);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("updateAirport/{id}")
	public ResponseEntity updateAirport(@PathVariable int id, @RequestBody Airport airportParam) {
		try {
			if (airportParam == null) {
				return new ResponseEntity("Airport can not be null", HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(airportParam.getName())) {
				return new ResponseEntity("Airport name can not be empty", HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(airportParam.getCode())) {
				return new ResponseEntity("Airport code can not be empty", HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(airportParam.getCity())) {
				return new ResponseEntity("Airport city can not be empty", HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(airportParam.getCountry())) {
				return new ResponseEntity("Airport country can not be empty", HttpStatus.NOT_FOUND);
			}

			Airport oldAirport = airportService.getAirportById(id);

			if(oldAirport == null || oldAirport.getId() == 0) {
				return new ResponseEntity("Airport can not be found", HttpStatus.NOT_FOUND);
			}
			airportService.updateAirport(oldAirport, airportParam);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/searchAirportByName/{name}")
	public ResponseEntity searchAirport(@PathVariable String name) {
		try {
			return new ResponseEntity(airportService.searchAirportByName(name), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
