package billing.service

import org.springframework.web.reactive.function.server.coRouter

class Router(private val handler: Handler) {

    fun routes() = coRouter {
        GET("/billing", handler::getBilling)
    }
}