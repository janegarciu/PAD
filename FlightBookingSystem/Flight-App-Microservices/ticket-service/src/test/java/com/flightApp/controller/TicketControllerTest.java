package com.flightApp.controller;

import com.flightApp.service.TicketService;
import com.flightapp.dto.TicketDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    public static final long TICKET_ID = 1L;
    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @Test
    void getTickets() {
        final var expectedTicketDto = TicketDto.builder().build();

        when(ticketService.findById(TICKET_ID)).thenReturn(expectedTicketDto);

        final var actualTicketDto = ticketController.getTickets(TICKET_ID);

        assertEquals(actualTicketDto, expectedTicketDto);
    }
}