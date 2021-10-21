package com.flightApp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Config

  object HystrixDashboardApplication extends App {
    SpringApplication.run(classOf[Config], args: _*)
  }

