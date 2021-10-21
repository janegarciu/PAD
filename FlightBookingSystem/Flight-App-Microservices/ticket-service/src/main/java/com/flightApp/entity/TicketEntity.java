package com.flightApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @Column(name = "id_flight")
    private Long flightId;
}
