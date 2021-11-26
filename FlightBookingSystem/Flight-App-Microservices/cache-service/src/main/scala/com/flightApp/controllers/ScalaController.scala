package com.flightApp.controllers

import java.util
import java.util.{ArrayList, Collections, List}

import com.flightApp.feign.FlightServiceFeign
import com.flightapp.dto.FlightDto
import io.vavr.collection.List
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/"))
class ScalaController extends ApplicationContextAware {

  @GetMapping(Array("/get-all-flights"))
  @Cacheable(value = Array("flightCache"))
  def cacheFlight(): util.List[FlightDto] = {
    val flightServiceFeign = context.getBean(classOf[FlightServiceFeign]);
    val circuitBreakerFactory = context.getBean(classOf[Resilience4JCircuitBreakerFactory]);
    val circuitBreaker = circuitBreakerFactory.create("circuitBreakerFactory");
    val emptyList: util.List[FlightDto] = Collections.emptyList()
    val flights = circuitBreaker.run(() => flightServiceFeign.getFlights, (e: Throwable) => emptyList)
    return flights;
  }

  var context: ApplicationContext = null;

  override def setApplicationContext(applicationContext: ApplicationContext): Unit = {

    context = applicationContext;
  }
}

