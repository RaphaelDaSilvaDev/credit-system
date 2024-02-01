package com.raphaelsilva.credit.system.repositories

import com.raphaelsilva.credit.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long>