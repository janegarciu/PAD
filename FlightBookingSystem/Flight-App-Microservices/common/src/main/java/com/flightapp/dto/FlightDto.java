package com.flightapp.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@RequiredArgsConstructor
@Builder
public class FlightDto implements Serializable {

    private String flightNumber;

    private String departureCity;

    private String departureAirport;

    private String destinationCity;

    private String destinationAirport;
}
