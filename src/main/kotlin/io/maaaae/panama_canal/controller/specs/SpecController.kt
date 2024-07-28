package io.maaaae.panama_canal.controller.specs

import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.service.SpecsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/specs")
class SpecController(private val specsService: SpecsService) {

    @GetMapping("/all")
    fun getAllApiInfo(): ResponseEntity<List<SpecsDto>> {
        val apiInfos = specsService.getAllApiSpecs()
        return ResponseEntity.ok(apiInfos)
    }

    @PostMapping
    fun createApiInfo(@RequestBody @Valid specsRequest: SpecsRequest): ResponseEntity<SpecsDto> {
        val createdSpec = specsService.createApiSpecs(specsRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpec)
    }
}