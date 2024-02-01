package com.raphaelsilva.credit.system.controller

import com.raphaelsilva.credit.system.dto.CreditDto
import com.raphaelsilva.credit.system.dto.CreditView
import com.raphaelsilva.credit.system.dto.CreditViewList
import com.raphaelsilva.credit.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
        private val creditService: CreditService,
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val createdCredit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Credit ${createdCredit.creditCode} - Customer ${createdCredit.customer?.firstName} requested")
    }

    @GetMapping
    fun getAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> =
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(creditService.findAllByCustomer(customerId).stream().map {
                        CreditViewList(it)
                    }.collect(Collectors.toList()))


    @GetMapping("/{creditCode}")
    fun getByCreditCode(@RequestParam(value = "customerId") customerId: Long, @PathVariable creditCode: UUID): ResponseEntity<CreditView> =
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CreditView(creditService.findByCreditCode(customerId, creditCode)))

}