package com.example.moviback.config

import com.auth0.jwt.algorithms.Algorithm
import com.example.moviback.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTVerificationException


@Component
class JwtUtil {
    private val SECRET_KEY = "s3cr37"
    private val ALGORITHM: Algorithm = Algorithm.HMAC256(SECRET_KEY)

    // No es necesario inyectar UserRepository si no lo estás usando para roles
    // @Autowired
    // lateinit var userRepository: UserRepository

    fun create(username: String?): String? {
        // En lugar de obtener roles de la base de datos, asigna "admin" directamente
        val roles: Array<String?> = arrayOf("admin")
        return JWT.create()
            .withArrayClaim("roles", roles)
            .withSubject(username)
            .withIssuer("app-admin")
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
            .sign(ALGORITHM)
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
            true
        } catch (e: JWTVerificationException) {
            false
        }
    }

    fun getUsername(jwt: String?): String? {
        return JWT.require(ALGORITHM)
            .build()
            .verify(jwt)
            .subject
    }
}
