package com.raphaelsilva.credit.system.controller

import com.raphaelsilva.credit.system.dto.request.CustomerDto
import com.raphaelsilva.credit.system.dto.request.CustomerUpdateDto
import com.raphaelsilva.credit.system.dto.response.CustomerView
import com.raphaelsilva.credit.system.entity.Customer
import com.raphaelsilva.credit.system.service.impl.CustomerService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer")
class CustomerController(private val customerService: CustomerService) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "201 Created", description = "Customer {email} saved!"),
        ApiResponse(responseCode = "400 Bad Request", description = "Some field was wrong"),
        ApiResponse(responseCode = "409 Conflict", description = "Some violation was find")
    ])
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Customer ${savedCustomer.email} saved!")
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200 Ok", description = "Return Customer"),
        ApiResponse(responseCode = "400 Bad Request", description = "Id not found"),
    ])
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val getCustomer: Customer = customerService.findById(id)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CustomerView(getCustomer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = [
        ApiResponse(responseCode = "400 Bad Request", description = "Id not found"),
    ])
    fun deleteCustomer(@PathVariable id: Long) {
        customerService.delete(id)
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200 Ok", description = "Return Updated Customer"),
        ApiResponse(responseCode = "400 Bad Request", description = "Id not found"),
    ])
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