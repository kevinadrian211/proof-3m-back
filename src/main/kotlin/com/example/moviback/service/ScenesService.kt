package com.example.moviback.service

import com.example.moviback.entity.Scenes
import com.example.moviback.repository.ScenesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ScenesService {
    @Autowired
    lateinit var scenesRepository: ScenesRepository

    fun list(): List<Scenes> {
        return scenesRepository.findAll()
    }

    fun save(scenes: Scenes): Scenes {
        return scenesRepository.save(scenes)
    }

    fun update(scenes: Scenes): Scenes {
        try {
            scenesRepository.findById(scenes.id)
                ?: throw Exception("Scenes not found")
            return scenesRepository.save(scenes)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(scenes: Scenes): Scenes {
        try {
            val response = scenesRepository.findById(scenes.id)
                ?: throw Exception("Scenes not found")
            response.apply {
                titles = scenes.titles
            }
            return scenesRepository.save(scenes)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val scenesOptional = scenesRepository.findById(id)
            if (scenesOptional.isPresent) {
                val scenes = scenesOptional.get()
                scenesRepository.delete(scenes)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No Scenes found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting scenes", ex)
        }
    }
}