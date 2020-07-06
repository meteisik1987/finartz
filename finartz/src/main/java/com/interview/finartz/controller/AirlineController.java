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

import com.interview.finartz.entity.Airline;
import com.interview.finartz.service.AirlineService;

@RestController
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	@GetMapping("/airlines")
	public List<Airline> getAirlines() {
		try {
			return airlineService.findAllAirlines();
		} catch (Exception e) {
			return new ArrayList<Airline>();
		}
	}

	@PostMapping("/addAirline")
	public ResponseEntity createAirline(@RequestBody Airline airline) {
		try {
			return new ResponseEntity(airlineService.createAirline(airline), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteAirline/{id}")
	public ResponseEntity deleteAirline(@PathVariable int id) {
		try {
			airlineService.deleteAirline(id);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("updateAirline/{id}")
	public ResponseEntity updateAirline(@PathVariable int id, @RequestBody Airline airlineParam) {
		try {
			if (airlineParam == null) {
				return new ResponseEntity("Airline can not be null", HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(airlineParam.getName())) {
				return new ResponseEntity("Airline name can not be empty", HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(airlineParam.getCode())) {
				return new ResponseEntity("Airline code can not be empty", HttpStatus.NOT_FOUND);
			}
			Airline oldAirline = airlineService.getAirlineById(id);
			if(oldAirline == null || oldAirline.getId() == 0) {
				return new ResponseEntity("Airline can not be found", HttpStatus.NOT_FOUND);
			}
			airlineService.updateAirline(oldAirline, airlineParam);
			return new ResponseEntity("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/searchAirlineByName/{name}")
	public ResponseEntity searchAirline(@PathVariable String name) {
		try {
			return new ResponseEntity(airlineService.searchAirlineByName(name), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
