package com.flightApp.controller;

import com.flightApp.service.FlightService;
import com.flightapp.dto.FlightDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightService flightService;


    @Test
    void findAll() {
        final var expectedFlight = FlightDto.builder().build();

        when(flightService.findAll()).thenReturn(List.of(expectedFlight));

        final var actualFlights = flightController.findAll();

        assertTrue(actualFlights.contains(expectedFlight));
    }

    @Test
    void getFlightById() {
        final var expectedFlight = FlightDto.builder().build();

        when(flightService.findById(1L)).thenReturn(expectedFlight);

        final var actualFlight = flightController.getFlight(1L);

        assertEquals(expectedFlight,actualFlight);
    }




}