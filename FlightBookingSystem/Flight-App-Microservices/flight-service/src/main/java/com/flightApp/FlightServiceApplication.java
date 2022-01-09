package com.flightApp;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.appender.listener.AppenderListener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class FlightServiceApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FlightServiceApplication.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("Flight Server");
    }
}
