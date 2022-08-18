package com.codely.api.application

import com.codely.api.domain.course.Course
import com.codely.api.domain.course.CourseRepository
import java.time.LocalDateTime
import java.util.UUID

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course(UUID.fromString(id), name, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
