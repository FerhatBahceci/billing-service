package billing.service.service

import billing.service.gateway.BillingGateway

class BillingService(private val gateway: BillingGateway) {
    suspend fun getBilling(name: String) =
        gateway.getBilling(name)
}