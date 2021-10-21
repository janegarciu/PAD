package com.flightApp.service;

import com.flightApp.entity.TicketEntity;
import com.flightApp.feign.FlightServiceClient;
import com.flightApp.mapper.TicketDtoMapper;
import com.flightApp.repository.TicketRepository;
import com.flightapp.dto.TicketDto;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketDtoMapper ticketDtoMapper;
    private final FlightServiceClient flightServiceClient;


    public TicketService(final TicketRepository ticketRepository,
                         final TicketDtoMapper ticketDtoMapper,
                         final FlightServiceClient flightServiceClient) {
        this.ticketRepository = ticketRepository;
        this.ticketDtoMapper = ticketDtoMapper;
        this.flightServiceClient = flightServiceClient;
    }

    public TicketDto findById(final Long ticketId) {
        final TicketEntity ticketEntity = ticketRepository.findById(ticketId).orElseThrow(RuntimeException::new);
        final TicketDto ticketDto = ticketDtoMapper.map(ticketEntity);
        ticketDto.setFlight(flightServiceClient.getFlight(ticketEntity.getFlightId()));
        return ticketDto;
    }
}
