package com.codely.course.application

import com.codely.course.domain.Course
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseName
import com.codely.course.domain.CourseRepository
import com.codely.course.domain.Publisher

class CourseCreator(private val repository: CourseRepository, private val publisher: Publisher) {

    fun create(id: String, name: String) {
        Course.create(CourseId.fromString(id), CourseName(name)).let {
            repository.save(it)
            publisher.publish(it.events)
        }
    }
}
