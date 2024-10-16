package io.maaaae.panama_canal.service.menu

import io.maaaae.panama_canal.dto.category.toMenuResponse
import io.maaaae.panama_canal.dto.menu.MenuResponse
import io.maaaae.panama_canal.repository.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class MenuServiceImpl(
    private val categoryRepository: CategoryRepository
): MenuService {
    override fun getMenus(): List<MenuResponse> =
        categoryRepository.findAll().map { it.toMenuResponse() }.toList()
}