package com.codely.course.application

<<<<<<< Updated upstream:04-02-repository-test/src/main/kotlin/com/codely/course/application/CourseCreator.kt
import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.course.domain.course.CourseRepository
=======
import com.codely.course.domain.Course
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseName
import com.codely.course.domain.CourseRepository
import org.springframework.stereotype.Component
>>>>>>> Stashed changes:03-01-dependency-injection/contexts/course/src/main/kotlin/com/codely/course/application/CourseCreator.kt
import java.time.LocalDateTime

class CourseCreator(private val repository: CourseRepository) {

    fun create(id: String, name: String) {
        Course(CourseId.fromString(id), CourseName(name), LocalDateTime.now()).let {
            repository.save(it)
        }
    }
}
