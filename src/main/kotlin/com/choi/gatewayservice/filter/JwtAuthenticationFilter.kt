package com.choi.gatewayservice.filter

import com.choi.gatewayservice.util.JwtUtil
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request = exchange.request
        val authHeader = request.headers["Authorization"]?.firstOrNull()

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            return exchange.response.setComplete()
        }

        val token = authHeader.substring(7)
        if (!jwtUtil.validateToken(token)) {
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            return exchange.response.setComplete()
        }

        return chain.filter(exchange)
    }
}
