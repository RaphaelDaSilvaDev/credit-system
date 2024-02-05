package com.raphaelsilva.credit.system.service

import com.raphaelsilva.credit.system.entity.Address
import com.raphaelsilva.credit.system.entity.Customer
import com.raphaelsilva.credit.system.exceptions.BusinessException
import com.raphaelsilva.credit.system.repositories.CustomerRepository
import com.raphaelsilva.credit.system.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        val savedCustomer: Customer = customerService.save(fakeCustomer)

        Assertions.assertThat(savedCustomer).isNotNull
        Assertions.assertThat(savedCustomer).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerService.save(fakeCustomer) }
    }

    @Test
    fun `should get customer by id`(){
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

        val getCustomer = customerService.findById(fakeId)

        Assertions.assertThat(getCustomer).isNotNull
        Assertions.assertThat(getCustomer).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(getCustomer).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should not get customer by invalid id and throw BusinessException`(){
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
                .isThrownBy { customerService.findById(fakeId) }
                .withMessage("Id $fakeId not found")

        verify (exactly = 1){ customerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`(){
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        customerService.delete(fakeId)

        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }

    }

    private fun buildCustomer(
            firstName: String = "Test Customer Name",
            lastName: String = "Test Customer LastName",
            document: String = "89941549060",
            income: BigDecimal = BigDecimal("1500.0"),
            email: String = "test@customer.email",
            password: String = "1234",
            zipCode: String = "12345",
            street: String = "Customer Street",
            id: Long = 1L
    ) = Customer(
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
            id = id
    )
}