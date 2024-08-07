package io.maaaae.panama_canal.service.dynamic_route_config

import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse
import io.maaaae.panama_canal.dto.filter_config.toCreateEntity
import io.maaaae.panama_canal.dto.filter_config.toResponse
import io.maaaae.panama_canal.repository.dynamic_route_config.DynamicRouteConfigRepository
import io.maaaae.panama_canal.repository.filter_config.FilterConfigRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DynamicRouteConfigServiceImpl(private val dynamicRouteConfigRepository: DynamicRouteConfigRepository): DynamicRouteConfigService {


    @Transactional
    override fun createDynamicRouteConfig(dynamicRouteConfigRequest: DynamicRouteConfigRequest) {
        dynamicRouteConfigRepository.save(dynamicRouteConfigRequest.toCreateEntity())
    }


    @Transactional
    override fun getAllDynamicRouteConfigs(): List<DynamicRouteConfigResponse> {
        return dynamicRouteConfigRepository.findAll().asSequence()
            .map { it.toResponse() }
            .toList()
    }

    @Transactional
    override fun updateDynamicRouteConfig(id: Long, dynamicRouteConfigRequest: DynamicRouteConfigRequest) {
        val routeConfig = dynamicRouteConfigRepository.findById(id)
            .orElseThrow {
                NoSuchElementException("category not found.")
            }

        routeConfig.update(dynamicRouteConfigRequest)
    }

    @Transactional
    override fun deleteDynamicRouteConfig(id: Long) {
        dynamicRouteConfigRepository.deleteById(id)
    }
}

private fun DynamicRouteConfigRequest.toCreateEntity(): DynamicRouteConfig {
    TODO("Not yet implemented")
}
