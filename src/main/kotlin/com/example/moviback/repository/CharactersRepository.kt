package com.example.moviback.repository

import com.example.moviback.entity.Characters
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharactersRepository: JpaRepository<Characters, Long> {
    fun findById(id: Long?): Characters?
}