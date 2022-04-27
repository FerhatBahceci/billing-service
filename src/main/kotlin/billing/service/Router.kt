package billing.service

import org.springframework.web.reactive.function.server.coRouter

class Router(private val baseUrl : String, private val handler: Handler) {

    fun routes() = coRouter {

        baseUrl.nest {
            GET("/billing", handler::getBilling)
        }
    }
}