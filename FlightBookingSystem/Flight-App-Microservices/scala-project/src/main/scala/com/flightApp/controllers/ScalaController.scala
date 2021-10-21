package com.flightApp.controllers

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/"))
class ScalaController {

  @GetMapping(Array("/"))
  @LoadBalanced
  def pohui(): String = {
    return "scala";
  }
}
