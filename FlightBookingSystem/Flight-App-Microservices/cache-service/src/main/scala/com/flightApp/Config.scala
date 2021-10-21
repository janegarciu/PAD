package com.flightApp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableFeignClients
class Config

  object CacheService extends App {
    SpringApplication.run(classOf[Config], args: _*)
  }

