package com.codely.api.application

import com.codely.api.domain.course.Course
import com.codely.api.domain.course.CourseId
import com.codely.api.domain.course.CourseName
import com.codely.api.domain.course.CourseRepository
import java.time.LocalDateTime

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course(CourseId.fromString(id), CourseName(name), LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
