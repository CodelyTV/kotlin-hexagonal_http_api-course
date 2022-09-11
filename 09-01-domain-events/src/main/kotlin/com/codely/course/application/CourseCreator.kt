package com.codely.course.application

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.course.domain.course.CourseRepository
import com.codely.course.domain.course.Publisher

class CourseCreator(private val repository: CourseRepository, private val publisher: Publisher) {

    fun create(id: String, name: String) {
        Course.create(CourseId.fromString(id), CourseName(name)).let {
            repository.save(it)
            publisher.publish(it.events)
        }
    }
}
