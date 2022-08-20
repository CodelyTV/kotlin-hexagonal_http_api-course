package com.codely.shared.config

import com.codely.course.application.CourseCreator
import com.codely.course.domain.course.CourseRepository
import com.codely.course.infrastructure.persistence.InMemoryCourseRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConf {

    @Bean
    fun courseRepository() = InMemoryCourseRepository()

    @Bean
    fun courseCreator(courseRepository: CourseRepository) = CourseCreator(courseRepository)
}
