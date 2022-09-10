package shared.persistence

import io.mockk.unmockkAll
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import org.junit.jupiter.api.AfterEach
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer

@ActiveProfiles("test")
open class BaseIntegrationTest {

    companion object {
        @JvmStatic
        val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14.5")
            .withDatabaseName("course_database")
            .withUsername("course_username")
            .withPassword("course_password")

        @PostConstruct
        fun start() {
            postgresContainer.start()
        }

        @PreDestroy
        fun stop() {
            postgresContainer.stop()
        }
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}
