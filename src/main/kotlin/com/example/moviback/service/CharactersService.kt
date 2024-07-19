package com.example.moviback.service

import com.example.moviback.entity.Characters
import com.example.moviback.repository.CharactersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharactersService {
    @Autowired
    lateinit var charactersRepository: CharactersRepository

    fun list(): List<Characters> {
        return charactersRepository.findAll()
    }

    fun save(characters: Characters): Characters {
        return charactersRepository.save(characters)
    }

    fun update(characters: Characters): Characters {
        try {
            charactersRepository.findById(characters.id)
                ?: throw Exception("Characters not found")
            return charactersRepository.save(characters)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(characters: Characters): Characters {
        try {
            val response = charactersRepository.findById(characters.id)
                ?: throw Exception("Characters not found")
            response.apply {
                namec = characters.namec
            }
            return charactersRepository.save(characters)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            val charactersOptional = charactersRepository.findById(id)
            if (charactersOptional.isPresent) {
                val characters = charactersOptional.get()
                charactersRepository.delete(characters)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "No characters found with provided ID")
            }
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting characters", ex)
        }
    }
}