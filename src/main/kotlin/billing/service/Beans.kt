package billing.service

import billing.service.gateway.BillingGatewayImpl
import billing.service.service.BillingService
import org.springframework.context.support.beans

fun beans() =

    beans {
        bean { BillingGatewayImpl() }
        bean { BillingService(ref()) }
        bean { Handler(ref()) }
        bean { Router(ref(), ref()).routes() }
    }
