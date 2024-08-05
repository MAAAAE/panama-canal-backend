package io.maaaae.panama_canal.service.filter_config

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse

interface FilterConfigService {
    fun createFilterConfig(filterConfigRequest: FilterConfigRequest)
    fun deleteCategory(id: Long)

    fun getAllFilterConfigs(): List<FilterConfigResponse>
    fun updateFilterConfig(id: Long, filterConfigRequest: FilterConfigRequest)
}