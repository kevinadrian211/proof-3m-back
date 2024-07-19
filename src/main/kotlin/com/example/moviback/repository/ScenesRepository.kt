package com.example.moviback.repository

import com.example.moviback.entity.Scenes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScenesRepository: JpaRepository<Scenes, Long> {
    fun findById(id: Long?): Scenes?
}