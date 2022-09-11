package com.codely.course.domain

interface CourseRepository {
    fun save(course: Course)
}
