package com.pts.gateway.filter;

import com.pts.common.exception.UnauthorizedException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class RequestFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Skip public endpoints
        if (path.startsWith("/auth") || path.startsWith("/actuator")) {
            return chain.filter(exchange);
        }

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .switchIfEmpty(Mono.error(new UnauthorizedException("Unauthenticated request")))
                .flatMap(auth -> {

                    ServerHttpRequest mutated =
                            exchange.getRequest().mutate()
                                    .header("X-User-Id", auth.getName())
                                    .header("X-User-Role",
                                            auth.getAuthorities()
                                                    .stream()
                                                    .findFirst()
                                                    .map(Object::toString)
                                                    .orElse("ROLE_USER"))
                                    .build();

                    return chain.filter(exchange.mutate()
                            .request(mutated)
                            .build());
                });
    }
}
