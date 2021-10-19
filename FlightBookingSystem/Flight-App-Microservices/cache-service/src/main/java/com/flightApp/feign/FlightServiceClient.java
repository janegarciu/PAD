package com.flightApp.feign;

import com.flightapp.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "flight-service")
public interface FlightServiceClient {

    @GetMapping("/")
    List<FlightDto> findAll();
}
