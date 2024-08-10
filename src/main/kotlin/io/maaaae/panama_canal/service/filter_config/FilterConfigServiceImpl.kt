package io.maaaae.panama_canal.service.filter_config

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import io.maaaae.panama_canal.dto.category.toCreateEntity
import io.maaaae.panama_canal.dto.category.toResponse
import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse
import io.maaaae.panama_canal.dto.filter_config.toCreateEntity
import io.maaaae.panama_canal.dto.filter_config.toResponse
import io.maaaae.panama_canal.repository.filter_config.FilterConfigRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FilterConfigServiceImpl(private val filterConfigRepository: FilterConfigRepository): FilterConfigService {


    @Transactional
    override fun createFilterConfig(filterConfigRequest: FilterConfigRequest) {
        TODO("NOT YET IMPLEMENTED")
    }


    @Transactional
    override fun getAllFilterConfigs(): List<FilterConfigResponse> {
        return filterConfigRepository.findAll().asSequence()
            .map { it.toResponse() }
            .toList()
    }

    @Transactional
    override fun updateFilterConfig(id: Long, filterConfigRequest: FilterConfigRequest) {
        val filterConfig = filterConfigRepository.findById(id)
            .orElseThrow {
                NoSuchElementException("category not found.")
            }

        filterConfig.update(filterConfigRequest)
    }

    @Transactional
    override fun deleteCategory(id: Long) {
        filterConfigRepository.deleteById(id)
    }
}