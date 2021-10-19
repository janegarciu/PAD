package com.flightApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "flights")
public class FlightEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "id_destination")
    private AirportEntity destination;

    @ManyToOne
    @JoinColumn(name = "id_departure")
    private AirportEntity departure;
}
