package com.flightapp.filter;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityFilter implements GatewayFilter {

    public static final String AUTHORIZATION = "Authorization";

    private final ConfigurableJWTProcessor<SecurityContext> configurableJWTProcessor;

    public SecurityFilter(final ConfigurableJWTProcessor<SecurityContext> configurableJWTProcessor) {
        this.configurableJWTProcessor = configurableJWTProcessor;
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {

        final var request = exchange.getRequest();
        final var tokenHeaderValues = request.getHeaders().get(AUTHORIZATION);

        if (ObjectUtils.isEmpty(tokenHeaderValues)) {
            return onError(exchange, "empty auth", HttpStatus.FORBIDDEN);
        }
        try {
            final var token = tokenHeaderValues.stream().findFirst().orElse(StringUtils.EMPTY);
            configurableJWTProcessor.process(token, null);
            return chain.filter(exchange);

        } catch (Exception e) {
            return onError(exchange, "invalid token", HttpStatus.FORBIDDEN);
        }

    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
