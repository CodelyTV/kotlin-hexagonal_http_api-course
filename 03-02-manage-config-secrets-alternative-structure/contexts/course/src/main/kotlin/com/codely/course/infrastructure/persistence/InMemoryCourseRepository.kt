package com.codely.course.infrastructure.persistence

import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository

class DatabaseConnectionData(var username: String = "", var password: String = "")

class InMemoryCourseRepository(connectionData: DatabaseConnectionData) : CourseRepository {

    override fun save(course: Course) {
        TODO("Not yet implemented")
    }
}
