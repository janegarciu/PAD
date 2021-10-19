package com.flightApp.mapper;

import com.flightApp.entity.FlightEntity;
import com.flightapp.dto.FlightDto;
import org.springframework.stereotype.Component;

@Component
public class FlightDtoMapper {

    public FlightDto map(FlightEntity source) {

        return FlightDto.builder()
                .flightNumber(source.getFlightNumber())
                .departureAirport(source.getDeparture().getName())
                .departureCity(source.getDeparture().getCity().getName())
                .destinationAirport(source.getDestination().getName())
                .destinationCity(source.getDestination().getCity().getName())
                .build();

    }
}
