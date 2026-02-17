package com.pts.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

    @Configuration
    @EnableWebFluxSecurity
    public  class SecurityConfig {

        @Bean
        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                             ReactiveJwtAuthenticationConverter jwtConverter) {

            return http
                    .csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authorizeExchange(exchange -> exchange

                            // Public
                            .pathMatchers("/auth/**").permitAll()
                            .pathMatchers("/actuator/health").permitAll()

                            // Role based
                            .pathMatchers("/user/admin/**").hasRole("ADMIN")
                            .pathMatchers("/user/**").hasAnyRole("USER", "ADMIN")

                            .anyExchange().authenticated()
                    )
                    .oauth2ResourceServer(oauth2 ->
                            oauth2.jwt(jwt ->
                                    jwt.jwtAuthenticationConverter(jwtConverter)
                            )
                    )
                    .build();
        }
    }

