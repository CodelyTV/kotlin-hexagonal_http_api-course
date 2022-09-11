package com.codely.course.application

import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository
import java.time.LocalDateTime
import java.util.UUID

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course(UUID.fromString(id), name, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
