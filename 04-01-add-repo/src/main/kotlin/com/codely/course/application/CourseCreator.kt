package com.codely.course.application

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.course.domain.course.CourseRepository
import java.time.LocalDateTime

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course.from(id, name, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
