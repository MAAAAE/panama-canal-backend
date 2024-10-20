package io.maaaae.panama_canal.service.filter_config

import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse
import io.maaaae.panama_canal.dto.filter_config.FilterConfigUpdateRequest
import io.maaaae.panama_canal.dto.filter_config.toCreateEntity
import io.maaaae.panama_canal.dto.filter_config.toResponse
import io.maaaae.panama_canal.repository.filter_config.FilterConfigRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FilterConfigServiceImpl(private val filterConfigRepository: FilterConfigRepository) : FilterConfigService {


    @Transactional
    override fun getAllFilterConfigs(): List<FilterConfigResponse> {
        return filterConfigRepository.findAll().asSequence()
            .map { it.toResponse() }
            .toList()
    }

    @Transactional
    override fun upsertFilterConfigs(
        filterConfigUpdateRequest: FilterConfigUpdateRequest,
        dynamicRouteConfig: DynamicRouteConfig
    ) {
        filterConfigRepository.findByIdOrNull(filterConfigUpdateRequest.id)
            ?.update(filterConfigUpdateRequest)
            ?: filterConfigRepository.save(
                filterConfigUpdateRequest.toCreateEntity(dynamicRouteConfig)
            )
    }

    @Transactional
    override fun deleteFilterConfig(id: Long) {
        filterConfigRepository.deleteById(id)
    }
}