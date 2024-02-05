package com.raphaelsilva.credit.system.dto.response

import com.raphaelsilva.credit.system.entity.Credit
import com.raphaelsilva.credit.system.enummeration.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallment: Int,
        val status: Status,
        val emailCustomer: String?,
        val incomeCustomer: BigDecimal?
) {
    constructor(credit: Credit): this(
          creditValue = credit.creditValue,
            creditCode = credit.creditCode,
            numberOfInstallment = credit.numberOfInstallments,
            status = credit.status,
            emailCustomer = credit.customer?.email,
            incomeCustomer = credit.customer?.income
    )
}
