package io.maaaae.panama_canal.service.dynamic_route_config

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigEvent
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigOptions
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigUpdateRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.toDynamicRouteConfigEntity
import io.maaaae.panama_canal.dto.dynamic_route_config.toDynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.toSimpleDynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.filter_config.toCreateEntity
import io.maaaae.panama_canal.repository.dynamic_route_config.DynamicRouteConfigRepository
import io.maaaae.panama_canal.repository.filter_config.FilterConfigRepository
import io.maaaae.panama_canal.service.filter_config.FilterConfigService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DynamicRouteConfigServiceImpl(
    private val dynamicRouteConfigRepository: DynamicRouteConfigRepository,
    private val filterConfigRepository: FilterConfigRepository,
    private val filterConfigService: FilterConfigService,
    private val eventPublisher: ApplicationEventPublisher
) : DynamicRouteConfigService {


    @Transactional
    override fun createDynamicRouteConfig(dynamicRouteConfigRequest: DynamicRouteConfigRequest) {
        val createdRouteConfig =
            dynamicRouteConfigRepository.save(dynamicRouteConfigRequest.toDynamicRouteConfigEntity())
        val targetFilters = dynamicRouteConfigRequest.filters.map { it.toCreateEntity(createdRouteConfig) }
        filterConfigRepository.saveAll(targetFilters)
            .apply { publishEvent("dynamic route config ${createdRouteConfig.id} created.") }
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

        request.filters?.forEach {
            filterConfigService.upsertFilterConfigs(
                filterConfigUpdateRequest = it,
                dynamicRouteConfig = routeConfig
            )
        }
        request.deletedFilters?.forEach { filterConfigService.deleteFilterConfig(it.id) }
        publishEvent("dynamic route config updated.")
    }

    @Transactional
    override fun deleteDynamicRouteConfig(id: Long) {
        val routeConfig = dynamicRouteConfigRepository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException("Dynamic Route not found. id: $id")
        dynamicRouteConfigRepository.delete(routeConfig).apply {
            publishEvent("dynamic route config $id deleted.")
        }
    }

    override fun publishEvent(message: String) {
        val event = DynamicRouteConfigEvent(message)
        eventPublisher.publishEvent(event)
    }
}
