package com.flightApp.feign

import java.util

import com.flightapp.dto.FlightDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestMethod}

@FeignClient(name = "flight-service")
trait FlightServiceFeign {

  @GetMapping(Array("/"))
  def getFlights: util.List[FlightDto]
}
