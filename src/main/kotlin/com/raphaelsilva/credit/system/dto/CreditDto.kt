package com.raphaelsilva.credit.system.dto

import com.raphaelsilva.credit.system.entity.Credit
import com.raphaelsilva.credit.system.entity.Customer
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        @field:NotNull(message = "Invalid Credit Value")
        val creditValue: BigDecimal,

        @field:Future
        val dayFirstOfInstallment: LocalDate,

        @field:NotNull(message = "Invalid Number of Installments")
        @field:Max(48, message = "The maximum number of installments is 48")
        @field:Min(1)
        val numberOfInstallments: Int,

        @field:NotNull(message = "Invalid Customer Id")
        val customerId: Long
) {

    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            numberOfInstallments = this.numberOfInstallments,
            dayFirstInstallment = this.dayFirstOfInstallment,
            customer = Customer(id = this.customerId)
    )
}
