package io.maaaae.panama_canal.controller.menu

import io.maaaae.panama_canal.dto.menu.MenuResponse
import io.maaaae.panama_canal.service.menu.MenuService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {
    @GetMapping
    fun getMenus(): ResponseEntity<List<MenuResponse>> =
        ResponseEntity.ok(menuService.getMenus())

}