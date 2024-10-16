package io.maaaae.panama_canal.service.filter_config

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse
import io.maaaae.panama_canal.dto.filter_config.FilterConfigUpdateRequest
import io.maaaae.panama_canal.dto.filter_config.toResponse
import io.maaaae.panama_canal.repository.filter_config.FilterConfigRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
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
    override fun updateFilterConfig(filterConfigUpdateRequest: FilterConfigUpdateRequest) {
        val filterConfig = filterConfigRepository.findByIdOrNull(filterConfigUpdateRequest.id)
            ?: throw ResourceNotFoundException("Filter Config not found. id: ${filterConfigUpdateRequest.id}")
        filterConfig.update(filterConfigUpdateRequest)
    }

    @Transactional
    override fun deleteCategory(id: Long) {
        filterConfigRepository.deleteById(id)
    }
}