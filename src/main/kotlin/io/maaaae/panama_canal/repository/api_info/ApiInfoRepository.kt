package io.maaaae.panama_canal.repository.api_info

import io.maaaae.panama_canal.domain.api_info.ApiInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ApiInfoRepository : JpaRepository<ApiInfo, Long>
