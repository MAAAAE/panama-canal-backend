package io.maaaae.panama_canal.controller.dynamic_route_config

import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigUpdateRequest
import io.maaaae.panama_canal.service.dynamic_route_config.DynamicRouteConfigService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/dynamic-route")
class DynamicRouteConfigController(private val dynamicRouteConfigService: DynamicRouteConfigService) {
    @GetMapping("/all")
    fun getCategories(): ResponseEntity<List<DynamicRouteConfigResponse>> {
        return ResponseEntity.ok(dynamicRouteConfigService.getAllDynamicRouteConfigs())
    }

    @PostMapping
    fun createCategory(@RequestBody dynamicRouteConfigRequest: DynamicRouteConfigRequest): ResponseEntity<Void> {
        dynamicRouteConfigService.createDynamicRouteConfig(dynamicRouteConfigRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @RequestBody dynamicRouteConfigRequest: DynamicRouteConfigUpdateRequest
    ): ResponseEntity<Void> {
        dynamicRouteConfigService.updateDynamicRouteConfig(id, dynamicRouteConfigRequest)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        dynamicRouteConfigService.deleteDynamicRouteConfig(id)
        return ResponseEntity(HttpStatus.OK)
    }
}