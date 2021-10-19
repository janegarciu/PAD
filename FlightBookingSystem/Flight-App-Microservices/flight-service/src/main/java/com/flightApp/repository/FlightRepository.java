package com.flightApp.repository;

import com.flightApp.entity.FlightEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepository extends CrudRepository<FlightEntity, Long> {

    List<FlightEntity> findAll();
}
