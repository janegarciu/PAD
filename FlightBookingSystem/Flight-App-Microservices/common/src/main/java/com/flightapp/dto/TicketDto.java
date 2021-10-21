package com.flightapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketDto {

    private Long price;
    private String ticketNumber;
    private FlightDto flight;
}
