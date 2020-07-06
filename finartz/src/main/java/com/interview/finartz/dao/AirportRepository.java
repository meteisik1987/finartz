package com.interview.finartz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interview.finartz.entity.Airport;

@Repository
@Transactional
public interface AirportRepository extends JpaRepository<Airport, Integer> {
	
	@Query("SELECT air FROM Airport air WHERE air.name LIKE CONCAT('%',:name,'%')")
	public List<Airport> findAirportsByName(@Param("name") String name);

}
