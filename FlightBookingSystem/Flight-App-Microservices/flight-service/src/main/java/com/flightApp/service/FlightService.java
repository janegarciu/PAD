package com.flightApp.service;

import com.flightApp.entity.FlightEntity;
import com.flightApp.mapper.FlightDtoMapper;
import com.flightApp.repository.FlightRepository;
import com.flightapp.dto.FlightDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightDtoMapper flightDtoMapper;

    public FlightService(final FlightRepository flightRepository, final FlightDtoMapper flightDtoMapper) {
        this.flightRepository = flightRepository;
        this.flightDtoMapper = flightDtoMapper;
    }

    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream().map(flightDtoMapper::map).collect(Collectors.toList());
    }

    public FlightDto fingById(final Long flightId) {
        final FlightEntity flightEntity = flightRepository.findById(flightId).orElseThrow(RuntimeException::new);

        return flightDtoMapper.map(flightEntity);
    }
}
