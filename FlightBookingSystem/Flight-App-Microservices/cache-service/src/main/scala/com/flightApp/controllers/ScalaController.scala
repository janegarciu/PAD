package com.flightApp.controllers

import java.util

import com.flightApp.feign.FlightServiceFeign
import com.flightapp.dto.FlightDto
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/"))
class ScalaController extends ApplicationContextAware {

  @GetMapping(Array("/get-all-flights"))
  @Cacheable(value = Array("flightCache"))
  def cacheFlight(): util.List[FlightDto] = {
    val flightServiceFeign = context.getBean(classOf[FlightServiceFeign]);
    return flightServiceFeign.getFlights;
  }

  var context: ApplicationContext = null;

  override def setApplicationContext(applicationContext: ApplicationContext): Unit = {

    context = applicationContext;
  }
}

