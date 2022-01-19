package com.flightApp

import java.time.Duration

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.circuitbreaker.resilience4j.{Resilience4JCircuitBreakerFactory, Resilience4JConfigBuilder}
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
//@EnableFeignClients
//@EnableScheduling
class Config

object CacheService extends App {
  SpringApplication.run(classOf[Config], args: _*)

//  @Bean def globalCustomConfiguration: Customizer[Resilience4JCircuitBreakerFactory] = {
//    val timeLimiterConfig: TimeLimiterConfig = TimeLimiterConfig.custom.timeoutDuration(Duration.ofSeconds(15)).build
//    val circuitBreakerConfig: CircuitBreakerConfig = CircuitBreakerConfig.custom.failureRateThreshold(50).waitDurationInOpenState(Duration.ofMillis(1000)).slidingWindowSize(2).build
//    return (factory: Resilience4JCircuitBreakerFactory) => factory.configure((builder: Resilience4JConfigBuilder) => builder.circuitBreakerConfig(circuitBreakerConfig).circuitBreakerConfig(circuitBreakerConfig).timeLimiterConfig(timeLimiterConfig).build, "circuitBreakerFactory")
//  }

}


