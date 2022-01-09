package com.flightapp;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class AuthorisationServerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthorisationServerApplication.class, args);
    }

    @Bean
    public AWSCognitoIdentityProvider cognitoIdentityProviderClient() {

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAYATR3R67ODABZIJP", "qf5uMu5s4qOSMzocfDM5Cx/nltAlh+729BBOydQ9")))
                .withRegion(Regions.US_EAST_2)
                .build();

    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("Authorisation server");
    }
}
