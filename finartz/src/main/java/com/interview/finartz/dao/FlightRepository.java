package com.interview.finartz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interview.finartz.entity.Flight;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query(value = "select * from interviewdb.flight flt where flt.code LIKE CONCAT('%',?1,'%')", nativeQuery=true)
	public List<Flight> findRouteByFlightCode(String flightCode);
	
	
	
	
}
