package io.maaaae.panama_canal.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { exchanges ->
                exchanges
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/specs/**").permitAll() // Debug
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().permitAll()
            }
            .oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }

        return http.build()
    }
}
