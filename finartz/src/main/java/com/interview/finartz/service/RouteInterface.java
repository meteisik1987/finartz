package com.interview.finartz.service;

import java.util.List;

import com.interview.finartz.entity.Route;

public interface RouteInterface {
	
	public List<Route> findAllRoutes();
	
	public Route createRoute(Route route);
	
	public void deleteRoute(int id);
	
	public void updateRoute(Route oldRoute, Route newRoute);
	
//	public ResponseEntity searchAirport(int departure, int arrival);

}
