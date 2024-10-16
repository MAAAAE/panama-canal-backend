package io.maaaae.panama_canal.service.gateway

import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class GatewayApiCaller(
    @Value("\${gateway.url}") private val baseUrl: String
) {

    private val webClient = WebClient.builder().baseUrl(baseUrl).build()

    @EventListener
    fun handleDynamicRouteConfigEvent(event: DynamicRouteConfigEvent) {
        println("Received event with message: ${event.message}")

        webClient.post()
            .uri("/routes/refresh")
            .retrieve()
            .bodyToMono(Void::class.java)
            .block()
    }
}
