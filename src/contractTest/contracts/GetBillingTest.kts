package contracts

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        url = url("/billing/ICA")
        method = GET
    }
    response {
        status = OK
/*
        body = bodyFromFileAsBytes("contracts/response.pdf")
*/
    }
}