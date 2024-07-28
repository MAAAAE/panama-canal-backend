package io.maaaae.panama_canal.controller.specs

import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import io.maaaae.panama_canal.service.SpecsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
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

    @PutMapping("/{id}")
    fun editApiSpec(
        @PathVariable id: Long,
        @RequestBody @Valid specsUpdateRequest: SpecsUpdateRequest
    ): ResponseEntity<SpecsDto> {
        val updatedSpec = specsService.updateApiSpec(specId = id, specsUpdateRequest = specsUpdateRequest)
        return ResponseEntity.ok(updatedSpec)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteApiSpec(@PathVariable id: Long) {
        specsService.deleteApiSpec(id)
    }
}