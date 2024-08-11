package io.maaaae.panama_canal.service.dynamic_route_config

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigUpdateRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigOptions
import io.maaaae.panama_canal.dto.dynamic_route_config.toDynamicRouteConfigEntity
import io.maaaae.panama_canal.dto.dynamic_route_config.toDynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.toSimpleDynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.filter_config.toCreateEntity
import io.maaaae.panama_canal.repository.dynamic_route_config.DynamicRouteConfigRepository
import io.maaaae.panama_canal.repository.filter_config.FilterConfigRepository
import io.maaaae.panama_canal.service.filter_config.FilterConfigService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DynamicRouteConfigServiceImpl(
    private val dynamicRouteConfigRepository: DynamicRouteConfigRepository,
    private val filterConfigRepository: FilterConfigRepository,
    private val filterConfigService: FilterConfigService,
) : DynamicRouteConfigService {


    @Transactional
    override fun createDynamicRouteConfig(dynamicRouteConfigRequest: DynamicRouteConfigRequest) {
        val createdRouteConfig = dynamicRouteConfigRepository.save(dynamicRouteConfigRequest.toDynamicRouteConfigEntity())
        val targetFilters = dynamicRouteConfigRequest.filters.map { it.toCreateEntity(createdRouteConfig) }
        filterConfigRepository.saveAll(targetFilters)
    }


    @Transactional(readOnly = true)
    override fun getAllDynamicRouteConfigs(): List<DynamicRouteConfigResponse> =
        dynamicRouteConfigRepository.findAll().asSequence()
            .map { it.toDynamicRouteConfigResponse() }
            .toList()


    @Transactional(readOnly = true)
    override fun getDynamicRouteConfigOptions(): List<DynamicRouteConfigOptions> =
        dynamicRouteConfigRepository.findAll().asSequence()
            .map { it.toSimpleDynamicRouteConfigResponse() }
            .toList()

    @Transactional
    override fun updateDynamicRouteConfig(id: Long, request: DynamicRouteConfigUpdateRequest) {
        val routeConfig = dynamicRouteConfigRepository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException("Dynamic Route not found. id: $id")
        routeConfig.update(request)

        // TODO: 필터에 대한 업데이트 및 삭제를 어떻게 할것인지 확인이 필요함.
        request.filters?.forEach { filterConfigService.updateFilterConfig(it) }
    }

    @Transactional
    override fun deleteDynamicRouteConfig(id: Long) {
        val routeConfig = dynamicRouteConfigRepository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException("Dynamic Route not found. id: $id")
        dynamicRouteConfigRepository.delete(routeConfig)
    }
}
