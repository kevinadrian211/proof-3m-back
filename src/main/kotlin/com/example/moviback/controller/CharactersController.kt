package com.example.moviback.controller

import com.example.moviback.entity.Characters
import com.example.moviback.service.CharactersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class CharactersController {
    @Autowired
    lateinit var charactersService: CharactersService

    @GetMapping
    fun list(): List<Characters> {
        return charactersService.list()
    }

    @PostMapping
    fun save(@RequestBody characters: Characters): Characters {
        return charactersService.save(characters)
    }

    @PutMapping
    fun update(@RequestBody characters: Characters): ResponseEntity<Characters> {
        return ResponseEntity(charactersService.update(characters), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody characters: Characters): ResponseEntity<Characters> {
        return ResponseEntity(charactersService.updateName(characters), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        charactersService.delete(id)
        return ResponseEntity.ok("characters deleted successfully")
    }
}