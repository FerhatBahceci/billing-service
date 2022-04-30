package billing.service.gateway

interface BillingGateway {
    suspend fun getBilling(name: String)
}