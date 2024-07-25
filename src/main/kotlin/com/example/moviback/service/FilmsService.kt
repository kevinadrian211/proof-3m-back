package com.example.moviback.service

import com.example.moviback.entity.Films
import com.example.moviback.repository.FilmsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmsService {

    @Autowired
    private lateinit var filmsRepository: FilmsRepository

    fun list(): List<Films> {
        return filmsRepository.findAll()
    }

    fun save(films: Films): Films {
        return filmsRepository.save(films)
    }

    fun update(films: Films): Films {
        try {
            filmsRepository.findById(films.id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Films not found")
            return filmsRepository.save(films)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(films: Films): Films {
        try {
            val existingFilm = filmsRepository.findById(films.id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Films not found")
            existingFilm.titlef = films.titlef
            return filmsRepository.save(existingFilm)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val filmsOptional = filmsRepository.findById(id)
            if (filmsOptional.isPresent) {
                filmsRepository.deleteById(id)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No films found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting films", ex)
        }
    }
}
