package com.flightApp.controller;

import com.flightApp.feign.service.FlightService;
import com.flightapp.dto.FlightDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class FlightController {

    private final FlightService flightService;

    public FlightController(final FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/")
    public List<FlightDto> findAll() {
        return flightService.findAll();
    }
}