package com.interview.finartz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.finartz.dao.AirlineRepository;
import com.interview.finartz.entity.Airline;
import com.interview.finartz.service.AirlineService;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService{

	@Autowired
	private AirlineRepository airlineRepository;	
	
	@Override
	public List<Airline> findAllAirlines() {
		return airlineRepository.findAll();
	}
	
	@Override
	public Airline getAirlineById(int id) {
		return airlineRepository.getOne(id);
	}

	@Override
	public Airline createAirline(Airline airline) {
		return airlineRepository.save(airline);
	}

	@Override
	public void deleteAirline(int id) {
		airlineRepository.deleteById(id);
	}

	@Override
	public Airline updateAirline(Airline oldAirline, Airline airlineParam) {
		oldAirline.setCode(airlineParam.getCode());
		oldAirline.setName(airlineParam.getName());
		return airlineRepository.save(oldAirline);		
	}

	@Override
	public List<Airline> searchAirlineByName(String name) {
		return airlineRepository.findAirlinesByName(name);
	}
	
	
}
