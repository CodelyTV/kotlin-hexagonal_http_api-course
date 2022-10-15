package com.codely.course.domain

sealed class CourseError(message: String) : Error()

data class CourseNotFoundError(val id: CourseId) : CourseError("The course with id <${id.value}> was not found")
data class CourseCannotBeFoundError(val id: CourseId) : CourseError("Something went wrong trying to find a course with id <${id.value}>")
