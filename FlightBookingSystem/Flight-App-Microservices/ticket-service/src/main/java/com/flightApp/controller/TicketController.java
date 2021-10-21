package com.flightApp.controller;

import com.flightApp.service.TicketService;
import com.flightapp.dto.TicketDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public TicketDto getTickets(@RequestParam final Long ticketId) {
        return ticketService.findById(ticketId);
    }
}
