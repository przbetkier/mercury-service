package integration

import com.przbetkier.mercury.MercuryApplication
import integration.common.WireMockRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(
        classes = MercuryApplication,
        properties = "application.environment=integration",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class IntegrationSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @LocalServerPort
    protected int port

    protected String localUrl(String endpoint) {
        return "http://localhost:$port$endpoint"
    }

    def setupSpec() {
        WireMockRunner.start()
    }

    void cleanup() {
        WireMockRunner.cleanupAll()
    }
}
