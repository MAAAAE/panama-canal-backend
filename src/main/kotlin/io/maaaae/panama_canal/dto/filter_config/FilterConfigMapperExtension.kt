package io.maaaae.panama_canal.dto.filter_config

import io.maaaae.panama_canal.common.exception.NonNullableFieldException
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.domain.FilterConfig
import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import sun.jvm.hotspot.oops.CellTypeState.value

fun FilterConfig.toResponse() = FilterConfigResponse(
//    id = categoryId,
//    name = name,
//    domain = domain,
//    secret = "secret",
//    description = description
)

fun FilterConfigRequest.toCreateEntity() = FilterConfig(
    filterName = "test",
    param = "test",
    value = "test",
    dynamicRouteConfig = DynamicRouteConfig(
        uri = "",
        predicates = "",
        filters = "",
        routeOrder = 1
    )
)

fun FilterConfig.setRoute(dynamicRouteConfig: DynamicRouteConfig) {

}
