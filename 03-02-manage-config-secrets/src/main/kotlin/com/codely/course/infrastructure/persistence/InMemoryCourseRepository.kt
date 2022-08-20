package com.codely.course.infrastructure.persistence

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseRepository
import com.codely.shared.config.DatabaseConnectionData

class InMemoryCourseRepository(private val connectionData: DatabaseConnectionData) : CourseRepository {

    init {
        println()
    }

    override fun save(course: Course) {
        TODO("Not yet implemented")
    }
}
