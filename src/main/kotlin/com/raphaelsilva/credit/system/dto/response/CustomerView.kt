package com.raphaelsilva.credit.system.dto.response

import com.raphaelsilva.credit.system.entity.Customer
import java.math.BigDecimal

data class CustomerView(
        val firstName: String,
        val lastName: String,
        val document: String,
        val income: BigDecimal,
        val email: String,
        val zipCode: String,
        val street: String
) {

    constructor(customer: Customer): this(
            firstName = customer.firstName,
            lastName = customer.lastName,
            document = customer.document,
            income = customer.income,
            email = customer.email,
            zipCode = customer.address.zipCode,
            street = customer.address.street
    )

}
