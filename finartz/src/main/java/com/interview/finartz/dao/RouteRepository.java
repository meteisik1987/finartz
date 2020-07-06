package com.interview.finartz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interview.finartz.entity.Route;

@Repository
@Transactional
public interface RouteRepository extends JpaRepository<Route, Integer> {
	
	@Query(value = "SELECT * FROM route rout WHERE rout.dep_airport_id= ?1 AND rout.arr_airport_id= ?2", nativeQuery=true)
	public List<Route> findRoute(int depId, int arrId);
	
	@Query(value = "SELECT * FROM route rout WHERE rout.dep_airport_id= ?1", nativeQuery=true)
	public List<Route> findRouteByDeparture(int depId);
	
	@Query(value = "SELECT * FROM route rout WHERE rout.arr_airport_id= ?1", nativeQuery=true)
	public List<Route> findRouteByArrival(int arrId);
	
	@Query(value = "SELECT * FROM interviewdb.route rout JOIN interviewdb.airport air WHERE air.name LIKE CONCAT('%',?1,'%')", nativeQuery=true)
	public List<Route> findRouteByAirportName(String airportName);
	

}
