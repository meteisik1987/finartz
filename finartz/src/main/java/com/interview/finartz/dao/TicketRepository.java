package com.interview.finartz.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interview.finartz.entity.Ticket;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("SELECT Count(id) FROM Ticket WHERE flight_id=?1")
	public int getTicketCountByFlightId(@Param("flight_id_param") int flightId);

}
