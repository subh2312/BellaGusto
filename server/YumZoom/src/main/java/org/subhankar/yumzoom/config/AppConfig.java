package org.subhankar.yumzoom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.subhankar.yumzoom.filter.AuthenticationFilter;

@Configuration
public class AppConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("USER-SERVICE", r -> r.path("/user/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://USER-SERVICE"))
                .route("USER-SERVICE", r -> r.path("/role/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://USER-SERVICE"))
                .route("AUTH-SERVICE", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://AUTH-SERVICE"))
                .route("RESTAURANT-SERVICE", r -> r.path("/restaurant/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://RESTAURANT-SERVICE"))
                .route("ADDRESS-SERVICE", r -> r.path("/address/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://ADDRESS-SERVICE"))
                .route("MENU-SERVICE", r -> r.path("/menu/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MENU-SERVICE"))
                .route("MENU-SERVICE", r -> r.path("/category/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MENU-SERVICE"))
                .route("MENU-SERVICE", r -> r.path("/item/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MENU-SERVICE"))
                .build();
    }
}
