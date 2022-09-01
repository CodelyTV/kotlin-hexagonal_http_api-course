package com.codely.course.infrastructure.persistence

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PostgreCourseRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : CourseRepository {

    override fun save(course: Course) {
        MapSqlParameterSource()
            .addValue("id", course.id.value)
            .addValue("name", course.name.value)
            .addValue("createdAt", course.createdAt)
            .let { params ->
                jdbcTemplate.update(
                    "INSERT INTO course (id, name, created_at) VALUES (:id,:name,:createdAt)",
                    params
                )
            }
    }

    override fun find(id: CourseId): Course {
        TODO("Not yet implemented")
    }
}
