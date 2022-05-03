package billing.service

import billing.service.gateway.BillingGateway
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(
    classes = [App::class])
abstract class AppBase {

	@LocalServerPort
    var port = 0

    @MockkBean
    lateinit var gateway: BillingGateway

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:" + this.port;

        coEvery {
            gateway.getBilling(any())
        } returns Unit
    }
}
