package com.example.moviback.controller

import com.example.moviback.entity.Films
import com.example.moviback.service.FilmsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/films")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class FilmsController {
    @Autowired
    lateinit var filmsService: FilmsService

    @GetMapping
    fun list(): List<Films> {
        return filmsService.list()
    }

    @PostMapping
    fun save(@RequestBody films: Films): Films {
        return filmsService.save(films)
    }

    @PutMapping
    fun update(@RequestBody films: Films): ResponseEntity<Films> {
        return ResponseEntity(filmsService.update(films), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody films: Films): ResponseEntity<Films> {
        return ResponseEntity(filmsService.updateName(films), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        filmsService.delete(id)
        return ResponseEntity.ok("films deleted successfully")
    }
}