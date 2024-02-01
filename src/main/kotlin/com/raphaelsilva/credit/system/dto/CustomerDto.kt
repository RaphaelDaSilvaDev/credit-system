package com.raphaelsilva.credit.system.dto

import com.raphaelsilva.credit.system.entity.Address
import com.raphaelsilva.credit.system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
        @field:NotEmpty(message = "Invalid First Name")
        val firstName: String,

        @field:NotEmpty(message = "Invalid Last Name")
        val lastName: String,

        @field:NotEmpty(message = "Invalid Document")
        @field:CPF(message = "Invalid Document")
        val document: String,

        @field:NotNull(message = "Invalid Income")
        val income: BigDecimal,

        @field:NotEmpty(message = "Invalid Email")
        @field:Email(message = "Invalid Email")
        val email: String,

        @field:NotEmpty(message = "Invalid Password")
        val password: String,

        @field:NotEmpty(message = "Invalid Zip Code")
        val zipCode: String,

        @field:NotEmpty(message = "Invalid Street")
        val street: String,
) {

    fun toEntity(): Customer = Customer(
         firstName = firstName,
            lastName = lastName,
            document = document,
            income = income,
            email = email,
            password = password,
            address = Address(
                    zipCode = zipCode,
                    street = street
            ),
    )

}
