package com.raphaelsilva.credit.system.service.impl

import com.raphaelsilva.credit.system.entity.Customer
import com.raphaelsilva.credit.system.exceptions.BusinessException
import com.raphaelsilva.credit.system.repositories.CustomerRepository
import com.raphaelsilva.credit.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository): ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)


    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow{
            throw BusinessException("Id $id not found")
        }


    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}