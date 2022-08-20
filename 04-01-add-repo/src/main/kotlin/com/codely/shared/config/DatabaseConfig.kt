package com.codely.shared.config

import com.codely.course.infrastructure.persistence.PostgreCourseRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate


@Configuration
class DatabaseConfig {

    @Bean
    fun courseRepository(jdbcTemplate: NamedParameterJdbcTemplate) = PostgreCourseRepository(jdbcTemplate)
}
