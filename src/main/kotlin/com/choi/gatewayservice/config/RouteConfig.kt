package com.choi.gatewayservice.config

import com.choi.gatewayservice.filter.JwtAuthenticationFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter) {

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("user-service") { r ->
                r.path("/api/users/**")
                    .filters { f -> f.filter(jwtAuthenticationFilter) }
                    .uri("http://user-service:8081")
            }
            .route("post-service") { r ->
                r.path("/api/posts/**")
                    .filters { f -> f.filter(jwtAuthenticationFilter) }
                    .uri("http://post-service:8082")
            }
            .route("comment-service") { r ->
                r.path("/api/comments/**")
                    .filters { f -> f.filter(jwtAuthenticationFilter) }
                    .uri("http://comment-service:8083")
            }
            .route("search-service") { r ->
                r.path("/api/search/**")
                    .filters { f -> f.filter(jwtAuthenticationFilter) }
                    .uri("http://search-service:8084")
            }
            .route("category-service") { r ->
                r.path("/api/categories/**")
                    .filters { f -> f.filter(jwtAuthenticationFilter) }
                    .uri("http://category-service:8085")
            }
            .build()
    }
}
