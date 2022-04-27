package billing.service

import billing.service.service.BillingService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

class Handler(private val service : BillingService) {

    suspend fun getBilling(request : ServerRequest) : ServerResponse {
        return ServerResponse.ok().build().awaitSingle()
    }
}