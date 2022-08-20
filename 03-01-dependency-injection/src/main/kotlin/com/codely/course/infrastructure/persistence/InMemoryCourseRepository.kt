package com.codely.course.infrastructure.persistence

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseRepository

class InMemoryCourseRepository() : CourseRepository {
    override fun save(course: Course) {
        TODO("Not yet implemented")
    }
}
