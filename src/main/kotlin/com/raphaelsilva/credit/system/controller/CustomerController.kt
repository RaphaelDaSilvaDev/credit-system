package com.raphaelsilva.credit.system.controller

import com.raphaelsilva.credit.system.dto.CustomerDto
import com.raphaelsilva.credit.system.dto.CustomerUpdateDto
import com.raphaelsilva.credit.system.dto.CustomerView
import com.raphaelsilva.credit.system.entity.Customer
import com.raphaelsilva.credit.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val getCustomer: Customer = customerService.findById(id)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomerView(getCustomer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) {
        customerService.delete(id)
    }

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long, @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {
        val getCustomer = customerService.findById(id)
        val toUpdateCustomer = customerUpdateDto.toEntity(getCustomer)
        val customerUpdated = this.customerService.save(toUpdateCustomer)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomerView(customerUpdated))
    }
}