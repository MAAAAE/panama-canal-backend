package io.maaaae.panama_canal.service.filter_config

import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse
import io.maaaae.panama_canal.dto.filter_config.FilterConfigUpdateRequest

interface FilterConfigService {
    fun createFilterConfig(filterConfigRequest: FilterConfigRequest)
    fun deleteCategory(id: Long)

    fun getAllFilterConfigs(): List<FilterConfigResponse>
    fun updateFilterConfig(filterConfigUpdateRequest: FilterConfigUpdateRequest)
}