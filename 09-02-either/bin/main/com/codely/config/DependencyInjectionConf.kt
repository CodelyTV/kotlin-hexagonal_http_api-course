package com.codely.config

import com.codely.course.application.CourseCreator
import com.codely.course.application.find.CourseFinder
import com.codely.course.domain.CourseRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConf {

    @Bean
    fun courseCreator(courseRepository: CourseRepository) = CourseCreator(courseRepository)

    @Bean
    fun courseFinder(courseRepository: CourseRepository) = CourseFinder(courseRepository)
}
