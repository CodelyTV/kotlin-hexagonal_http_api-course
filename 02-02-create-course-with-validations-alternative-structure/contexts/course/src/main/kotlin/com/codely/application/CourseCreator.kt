package com.codely.application

import com.codely.domain.course.Course
import com.codely.domain.course.CourseId
import com.codely.domain.course.CourseName
import com.codely.domain.course.CourseRepository
import java.time.LocalDateTime

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course(CourseId.fromString(id), CourseName(name), LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
