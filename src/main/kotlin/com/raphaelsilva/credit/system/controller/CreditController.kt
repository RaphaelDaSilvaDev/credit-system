package com.raphaelsilva.credit.system.controller

import com.raphaelsilva.credit.system.dto.request.CreditDto
import com.raphaelsilva.credit.system.dto.response.CreditView
import com.raphaelsilva.credit.system.dto.response.CreditViewList
import com.raphaelsilva.credit.system.service.impl.CreditService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
@Tag(name = "Credit")
class CreditController(
        private val creditService: CreditService,
) {
    @ApiResponses(value = [
        ApiResponse(responseCode = "201 Created", description = "Credit {credit code} - {customer name} requested"),
        ApiResponse(responseCode = "400 Bad Request", description = "Id not found or some field is wrong"),
    ])
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val createdCredit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Credit ${createdCredit.creditCode} - Customer ${createdCredit.customer?.firstName} requested")
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200 Ok", description = "Return array of credits")
    ])
    @GetMapping
    fun getAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> =
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(creditService.findAllByCustomer(customerId).stream().map {
                        CreditViewList(it)
                    }.collect(Collectors.toList()))


    @ApiResponses(value = [
        ApiResponse(responseCode = "201 Created", description = "Return credit"),
        ApiResponse(responseCode = "400 Bad Request", description = "Id not found or customer can't access credit"),
    ])
    @GetMapping("/{creditCode}")
    fun getByCreditCode(@RequestParam(value = "customerId") customerId: Long, @PathVariable creditCode: UUID): ResponseEntity<CreditView> =
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreditView(creditService.findByCreditCode(customerId, creditCode)))

}