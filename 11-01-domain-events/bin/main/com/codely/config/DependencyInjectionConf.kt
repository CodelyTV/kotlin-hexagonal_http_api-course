package com.codely.config

import com.codely.course.application.CourseCreator
import com.codely.course.application.find.CourseFinder
import com.codely.course.domain.CourseRepository
import com.codely.course.domain.Publisher
import com.codely.course.infrastructure.InMemoryPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConf {

    @Bean
    fun publisher() = InMemoryPublisher()

    @Bean
    fun courseCreator(courseRepository: CourseRepository, publisher: Publisher) =
        CourseCreator(courseRepository, publisher)

    @Bean
    fun courseFinder(courseRepository: CourseRepository) = CourseFinder(courseRepository)
}
