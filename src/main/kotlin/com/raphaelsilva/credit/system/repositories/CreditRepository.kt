package com.raphaelsilva.credit.system.repositories

import com.raphaelsilva.credit.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CreditRepository: JpaRepository<Credit, Long>{
    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value = "SELECT * FROM Credit WHERE Customer_id = ?1", nativeQuery = true)
    fun findAllByCustomer(customerId: Long): List<Credit>
}