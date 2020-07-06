package com.interview.finartz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interview.finartz.entity.Airline;

@Repository
@Transactional
public interface AirlineRepository extends JpaRepository<Airline, Integer> {

	@Query("SELECT air FROM Airline air WHERE air.name LIKE CONCAT('%',:name,'%')")
	public List<Airline> findAirlinesByName(@Param("name") String name);
	
}
