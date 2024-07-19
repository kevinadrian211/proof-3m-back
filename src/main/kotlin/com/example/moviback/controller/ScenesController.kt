package com.example.moviback.controller

import com.example.moviback.entity.Scenes
import com.example.moviback.service.ScenesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scenes")
class ScenesController {
    @Autowired
    lateinit var scenesService: ScenesService

    @GetMapping
    fun list(): List<Scenes> {
        return scenesService.list()
    }

    @PostMapping
    fun save(@RequestBody scenes: Scenes): Scenes {
        return scenesService.save(scenes)
    }

    @PutMapping
    fun update(@RequestBody scenes: Scenes): ResponseEntity<Scenes> {
        return ResponseEntity(scenesService.update(scenes), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody scenes: Scenes): ResponseEntity<Scenes> {
        return ResponseEntity(scenesService.updateName(scenes), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        scenesService.delete(id)
        return ResponseEntity.ok("scenes deleted successfully")
    }
}