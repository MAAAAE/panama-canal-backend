package io.maaaae.panama_canal.controller.dynamic_route_config

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import io.maaaae.panama_canal.service.dynamic_route_config.DynamicRouteConfigService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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
    fun updateCategory(@PathVariable id: Long, @RequestBody dynamicRouteConfigRequest: DynamicRouteConfigRequest): ResponseEntity<Void> {
        dynamicRouteConfigService.updateDynamicRouteConfig(id, dynamicRouteConfigRequest)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        dynamicRouteConfigService.deleteDynamicRouteConfig(id)
        return ResponseEntity(HttpStatus.OK)
    }
}