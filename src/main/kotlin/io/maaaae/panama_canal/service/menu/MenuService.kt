package io.maaaae.panama_canal.service.menu

import io.maaaae.panama_canal.dto.menu.MenuResponse

interface MenuService {
    fun getMenus(): List<MenuResponse>
}