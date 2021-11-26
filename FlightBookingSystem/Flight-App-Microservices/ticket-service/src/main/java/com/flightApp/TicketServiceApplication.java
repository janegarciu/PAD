package com.flightApp;

import com.flightapp.dto.FlightDto;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class TicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
		final var timeLimiterConfig = TimeLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(15))
				.build();
		final var circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofMillis(1000))
				.slidingWindowSize(2)
				.build();

		return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
				.circuitBreakerConfig(circuitBreakerConfig)
				.timeLimiterConfig(timeLimiterConfig)
				.build(), "circuitBreakerFactory");
	}

}
