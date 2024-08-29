package com.choi.gatewayservice.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtil {
    private val secret = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    private val expiration = 60000 * 60 // 1 hour

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secret)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}
