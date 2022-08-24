package course.persistence

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.course.infrastructure.persistence.PostgreCourseRepository
import com.codely.shared.Application
import java.sql.ResultSet
import java.time.LocalDateTime
import java.util.UUID
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import shared.persistence.BaseIntegrationTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [Application::class])
class PostgreCourseRepositoryTest: BaseIntegrationTest() {

    @Autowired
    private lateinit var repository: PostgreCourseRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun `should save a course`() {
        val courseId = "13590efb-c181-4c5f-9f95-b768abde13e2"
        val courseToSave = Course(CourseId.fromString(courseId), CourseName("Test"), LocalDateTime.now())
        repository.save(courseToSave)

        val courseFromDb = jdbcTemplate.queryForObject(
            "select * from course where id=?",
            mapRow(),
            courseId
        )

        assertEquals(courseToSave, courseFromDb)
    }

    private fun mapRow(): RowMapper<Course> {
        return RowMapper { rs: ResultSet, _: Int ->
            val id = CourseId(UUID.fromString(rs.getString("id")))
            val name = CourseName(rs.getString("name"))
            val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
            Course(id, name, createdAt)
        }
    }
}
