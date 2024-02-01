package com.raphaelsilva.credit.system.service.impl

import com.raphaelsilva.credit.system.entity.Credit
import com.raphaelsilva.credit.system.exceptions.BusinessException
import com.raphaelsilva.credit.system.repositories.CreditRepository
import com.raphaelsilva.credit.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(private val creditRepository: CreditRepository, private val customerService: CustomerService) :
        ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = credit.customer?.id?.let { customerService.findById(it) }
        }
        return this.creditRepository.save(credit)
    }


    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomer(customerId)


    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode) ?: throw BusinessException(
                "Creditor $creditRepository not found")
        return if (credit.customer?.id == customerId) credit else throw BusinessException(
                "This id $customerId, can't access this  Credit ")
    }
}