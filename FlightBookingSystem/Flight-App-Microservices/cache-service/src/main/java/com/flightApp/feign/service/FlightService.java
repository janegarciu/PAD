package com.flightApp.feign.service;

import com.flightApp.feign.FlightServiceClient;
import com.flightapp.dto.FlightDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightService {

    private final FlightServiceClient flightServiceClient;

    public FlightService(final FlightServiceClient flightServiceClient) {
        this.flightServiceClient = flightServiceClient;
    }

    @Cacheable(value = "flightCache")
    public List<FlightDto> findAll() {
        return flightServiceClient.findAll();
    }
}
