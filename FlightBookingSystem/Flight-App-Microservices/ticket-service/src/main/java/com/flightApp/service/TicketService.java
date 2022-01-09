package com.flightApp.service;

import com.flightApp.entity.TicketEntity;
import com.flightApp.feign.FlightServiceClient;
import com.flightApp.mapper.TicketDtoMapper;
import com.flightApp.repository.TicketRepository;
import com.flightapp.dto.FlightDto;
import com.flightapp.dto.TicketDto;
import io.vavr.collection.List;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketDtoMapper ticketDtoMapper;
    private final FlightServiceClient flightServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;


    public TicketService(final TicketRepository ticketRepository,
                         final TicketDtoMapper ticketDtoMapper,
                         final FlightServiceClient flightServiceClient,
                         final CircuitBreakerFactory circuitBreakerFactory) {
        this.ticketRepository = ticketRepository;
        this.ticketDtoMapper = ticketDtoMapper;
        this.flightServiceClient = flightServiceClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public TicketDto findById(final Long ticketId) {
        final TicketEntity ticketEntity = ticketRepository.findById(ticketId).orElseThrow(RuntimeException::new);
        final TicketDto ticketDto = ticketDtoMapper.map(ticketEntity);
        final var circuitBreaker = circuitBreakerFactory.create("circuitBreakerFactory");
        final var flight = circuitBreaker.run(() -> flightServiceClient.getFlight(ticketEntity.getFlightId()), e -> getDefaultFlight());
        final FlightDto flights = circuitBreaker.run(() -> flightServiceClient.getFlight(ticketEntity.getFlightId()), e -> getDefaultFlight());
        ticketDto.setFlight(flight);
        return ticketDto;
    }

    private FlightDto getDefaultFlight() {
        return FlightDto.builder().build();
    }
}
