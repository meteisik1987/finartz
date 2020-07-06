package com.interview.finartz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.finartz.dao.AirportRepository;
import com.interview.finartz.entity.Airport;
import com.interview.finartz.service.AirportService;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {

	@Autowired
	AirportRepository airportRepository;

	@Override
	public List<Airport> findAllAirports() {
		return airportRepository.findAll();
	}

	public Airport getAirportById(int id) {
		return airportRepository.getOne(id);
	}

	@Override
	public Airport createAirport(Airport airport) {
		return airportRepository.save(airport);
	}

	@Override
	public void deleteAirport(int id) {
		airportRepository.deleteById(id);
	}

	@Override
	public Airport updateAirport(Airport oldAirport, Airport airportParam) {
		oldAirport.setCode(airportParam.getCode());
		oldAirport.setName(airportParam.getName());
		oldAirport.setCity(airportParam.getCity());
		oldAirport.setCountry(airportParam.getCountry());
		return airportRepository.save(oldAirport);
	}

	@Override
	public List<Airport> searchAirportByName(String name) {
		return airportRepository.findAirportsByName(name);
	}

}
