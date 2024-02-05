package com.raphaelsilva.credit.system.dto.request

import com.raphaelsilva.credit.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
        val firstName: String?,
        val lastName: String?,
        val income: BigDecimal?,
        val zipCode: String?,
        val street: String?
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName ?: customer.firstName
        customer.lastName = this.lastName ?: customer.lastName
        customer.income = this.income ?: customer.income
        customer.address.zipCode = this.zipCode ?: customer.address.zipCode
        customer.address.street = this.street ?: customer.address.street
        return customer
    }
}
