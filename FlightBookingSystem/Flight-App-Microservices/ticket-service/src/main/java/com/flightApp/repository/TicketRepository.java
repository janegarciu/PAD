package com.flightApp.repository;

import com.flightApp.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
}
