package com.choi.gatewayservice.controller

import com.choi.gatewayservice.dto.LoginRequest
import com.choi.gatewayservice.dto.LoginResponse
import com.choi.gatewayservice.util.JwtUtil
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val jwtUtil: JwtUtil) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        // Here you would typically validate the credentials against a user service
        // For this example, we'll just generate a token
        val token = jwtUtil.generateToken(loginRequest.username)
        return LoginResponse(token)
    }
}