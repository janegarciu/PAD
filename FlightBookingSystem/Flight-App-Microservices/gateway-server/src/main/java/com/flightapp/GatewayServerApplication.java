package com.flightapp;

import com.flightapp.filter.SecurityFilter;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.net.MalformedURLException;
import java.net.URL;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayServerApplication {

    public static final String URL = "https://cognito-idp.us-east-2.amazonaws.com/us-east-2_WwyUqkmnf/.well-known/jwks.json";

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("flight-service", r -> r.path("/flight-booking-app/cache/**")
                        .filters(f -> f.filter(securityFilter).rewritePath("/flight-booking-app/cache/(?<remains>.*)", "/${remains}"))
                        .uri("lb://CACHE-SERVICE/"))
                .route("ticket-service", r -> r.path("/flight-booking-app/tickets/**")
                        .filters(f -> f.filter(securityFilter).rewritePath("/flight-booking-app/tickets/(?<remains>.*)", "/${remains}"))
                        .uri("lb://TICKET-SERVICE/"))

                .route("authorization-service", r -> r.path("/flight-booking-app/authorization/**")
                        .filters(f -> f.rewritePath("/flight-booking-app/authorization/(?<remains>.*)", "/${remains}"))
                        .uri("lb://AUTHORIZATION-SERVICE/"))
                .build();
    }

    @Bean
    public ConfigurableJWTProcessor<SecurityContext> configurableJWTProcessor() throws MalformedURLException {
        final var resourceRetriever = new DefaultResourceRetriever(2000, 2000);
        final var jwkSetURL = new URL(URL);
        final var keySource = new RemoteJWKSet<>(jwkSetURL, resourceRetriever);
        final var jwtProcessor = new DefaultJWTProcessor<>();
        final var keySelector = new JWSVerificationKeySelector<>(RS256, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);
        return jwtProcessor;
    }
}
