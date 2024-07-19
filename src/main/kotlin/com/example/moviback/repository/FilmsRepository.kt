package com.example.moviback.repository

import com.example.moviback.entity.Films
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilmsRepository:JpaRepository<Films, Long> {
    fun findById(id: Long?): Films?
}