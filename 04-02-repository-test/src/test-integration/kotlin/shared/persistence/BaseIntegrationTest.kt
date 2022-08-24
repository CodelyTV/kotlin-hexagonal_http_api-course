package shared.persistence

import com.codely.shared.Application
import io.mockk.unmockkAll
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@ActiveProfiles("test")
@Testcontainers
open class BaseIntegrationTest {
    @Container
    val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14.5")
        .withDatabaseName("course_database")
        .withUsername("course_username")
        .withPassword("course_password")


    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}
