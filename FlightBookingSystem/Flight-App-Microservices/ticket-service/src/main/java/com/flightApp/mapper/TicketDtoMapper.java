package com.flightApp.mapper;

import com.flightApp.entity.TicketEntity;
import com.flightapp.dto.FlightDto;
import com.flightapp.dto.TicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoMapper {
    public TicketDto map(final TicketEntity ticketEntity) {
        return TicketDto.builder()
                .price(ticketEntity.getPrice())
                .ticketNumber(ticketEntity.getTicketNumber()).build();
    }
}
