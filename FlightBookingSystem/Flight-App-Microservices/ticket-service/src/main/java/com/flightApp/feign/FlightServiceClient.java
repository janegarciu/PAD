package com.flightApp.feign;

import com.flightapp.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "flight-service")
public interface FlightServiceClient {

    @GetMapping("/{flightId}")
    public FlightDto getFlight(@PathVariable Long flightId);
}
