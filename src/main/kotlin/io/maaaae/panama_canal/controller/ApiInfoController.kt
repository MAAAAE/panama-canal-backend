package io.maaaae.panama_canal.controller

import io.maaaae.panama_canal.dto.ApiInfoDto
import io.maaaae.panama_canal.dto.ApiInfoRequest
import io.maaaae.panama_canal.service.ApiInfoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/info")
class ApiInfoController(private val apiInfoService: ApiInfoService) {

    @GetMapping("/all")
    fun getAllApiInfo(): ResponseEntity<List<ApiInfoDto>> {
        val apiInfos = apiInfoService.getAllApiInfo()
        return ResponseEntity.ok(apiInfos)
    }

    @PostMapping
    fun createApiInfo(@RequestBody @Valid apiInfoRequest: ApiInfoRequest): ResponseEntity<ApiInfoDto> {
        // TODO: Need to implement
        return ResponseEntity.status(HttpStatus.CREATED).body(null)
    }
}