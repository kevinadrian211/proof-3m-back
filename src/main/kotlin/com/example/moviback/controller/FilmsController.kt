package com.example.moviback.controller

import com.example.moviback.entity.Films
import com.example.moviback.service.FilmsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/films")
@CrossOrigin(
    origins = ["http://localhost:8081"],
    methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE],
    allowedHeaders = ["*"],
    allowCredentials = "true"
)
class FilmsController @Autowired constructor(
    private val filmsService: FilmsService
) {

    @GetMapping
    fun list(): ResponseEntity<List<Films>> {
        return ResponseEntity.ok(filmsService.list())
    }

    @PostMapping
    fun save(@RequestBody @Validated films: Films): ResponseEntity<Films> {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmsService.save(films))
    }

    @PutMapping
    fun update(@RequestBody @Validated films: Films): ResponseEntity<Films> {
        return ResponseEntity.ok(filmsService.update(films))
    }

    @PatchMapping
    fun updateName(@RequestBody @Validated films: Films): ResponseEntity<Films> {
        return ResponseEntity.ok(filmsService.updateName(films))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            filmsService.delete(id)
            ResponseEntity.ok("Film deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found")
        }
    }
}
