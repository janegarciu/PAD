package com.flightApp.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TicketController {

    @GetMapping
    public String getTickets() {
        return "Hello from Tickets";
    }
}
