package com.codely.course.application

import com.codely.course.domain.Course
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseName
import com.codely.course.domain.CourseRepository
import java.time.LocalDateTime

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course.from(id, name, LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
