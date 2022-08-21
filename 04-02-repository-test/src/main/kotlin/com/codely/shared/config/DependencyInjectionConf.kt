package com.codely.shared.config

import com.codely.course.application.CourseCreator
import com.codely.course.domain.course.CourseRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConf {

    @Bean
    fun courseCreator(courseRepository: CourseRepository) = CourseCreator(courseRepository)
}
