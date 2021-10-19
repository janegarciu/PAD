package com.flightapp.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class FlightDto {

    private String flightNumber;

    private String departureCity;

    private String departureAirport;

    private String destinationCity;

    private String destinationAirport;
}
