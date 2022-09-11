package shared.acceptance

import com.codely.shared.Application
import io.mockk.unmockkAll
import io.restassured.RestAssured
import java.io.File
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class BaseAcceptanceTest {
    @LocalServerPort
    private val springbootPort: Int = 0

    companion object {

        private const val POSTGRES_PORT = 5432
        val environment: DockerComposeContainer<*> =
            DockerComposeContainer(File("docker-compose.yml"))
                .withExposedService("db", POSTGRES_PORT, Wait.forListeningPort())
                .withLocalCompose(true)

        @JvmStatic
        @BeforeAll
        fun start() {
            environment.start()
        }

        @JvmStatic
        @AfterAll
        fun stop() {
            environment.stop()
        }
    }

    @BeforeEach
    fun setUp() {
        RestAssured.port = springbootPort
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}
