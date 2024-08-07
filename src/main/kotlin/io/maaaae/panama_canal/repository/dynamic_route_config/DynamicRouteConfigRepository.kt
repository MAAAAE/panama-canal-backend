package io.maaaae.panama_canal.repository.dynamic_route_config

import io.maaaae.panama_canal.domain.DynamicRouteConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DynamicRouteConfigRepository: JpaRepository<DynamicRouteConfig, Long> {

}
