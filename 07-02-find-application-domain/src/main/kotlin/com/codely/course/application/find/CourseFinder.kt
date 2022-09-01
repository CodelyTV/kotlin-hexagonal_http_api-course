package com.codely.course.application.find

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseRepository

class CourseFinder(private val courseRepository: CourseRepository) {
    fun execute(courseId: String) = CourseResponse(courseRepository.find(CourseId.fromString(courseId)))

}


data class CourseResponse(val data: Course)
