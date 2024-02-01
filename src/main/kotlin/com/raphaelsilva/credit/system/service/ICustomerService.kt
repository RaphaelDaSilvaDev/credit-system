package com.raphaelsilva.credit.system.service

import com.raphaelsilva.credit.system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}