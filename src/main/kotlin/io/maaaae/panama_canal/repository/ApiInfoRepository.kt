package io.maaaae.panama_canal.repository

import io.maaaae.panama_canal.domain.ApiInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ApiInfoRepository : JpaRepository<ApiInfo, Long> {
}