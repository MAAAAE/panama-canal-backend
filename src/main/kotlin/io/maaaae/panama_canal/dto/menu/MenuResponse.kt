package io.maaaae.panama_canal.dto.menu

data class MenuResponse(
    val id: Long,
    val label: String,
    val menuType: String = "CATEGORY_SUBMENU",
)
