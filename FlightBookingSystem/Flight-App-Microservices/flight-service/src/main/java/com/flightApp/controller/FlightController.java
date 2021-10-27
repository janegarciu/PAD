package com.flightApp.controller;

import com.flightApp.service.FlightService;
import com.flightapp.dto.FlightDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class FlightController {

    private final FlightService flightService;

    public FlightController(final FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/get-all-flights")
    public List<FlightDto> findAll() {
        return flightService.findAll();
    }

    @GetMapping("/get-flight/{flightId}")
    public FlightDto getFlight(@PathVariable final Long flightId) {
        return flightService.findById(flightId);
    }
}
