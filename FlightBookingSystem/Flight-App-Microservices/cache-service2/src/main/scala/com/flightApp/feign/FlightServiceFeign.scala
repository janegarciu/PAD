package com.flightApp.feign

import java.util

import com.flightapp.dto.FlightDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestMethod}

@FeignClient(name = "flight-service")
trait FlightServiceFeign {

  @GetMapping(Array("/get-all-flights"))
  def getFlights: util.List[FlightDto]
}
