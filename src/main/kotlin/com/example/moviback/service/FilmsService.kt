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

    @Autowired
    lateinit var FilmsRepository: FilmsRepository

    fun list(): List<Films> {
        return FilmsRepository.findAll()
    }

    fun save(films: Films): Films {
        return FilmsRepository.save(films)
    }

    fun update(films: Films): Films {
        try {
            filmsRepository.findById(films.id)
                ?: throw Exception("Films not found")
            return filmsRepository.save(films)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(films: Films): Films {
        try {
            val response = filmsRepository.findById(films.id)
            ?: throw Exception("Films not found")
            response.apply {
                titlef =films.titlef
            }
            return filmsRepository.save(films)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val filmsOptional = FilmsRepository.findById(id)
            if (filmsOptional.isPresent) {
                val films = filmsOptional.get()
                filmsRepository
            }else{
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No films found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting films", ex)
        }
    }
}