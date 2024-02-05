package com.raphaelsilva.credit.system.dto.response

import com.raphaelsilva.credit.system.entity.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallments: Int
) {
    constructor(credit: Credit): this(
            creditValue = credit.creditValue,
            creditCode = credit.creditCode,
            numberOfInstallments = credit.numberOfInstallments
    )
}
