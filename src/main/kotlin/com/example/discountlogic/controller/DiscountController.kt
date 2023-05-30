package com.example.discountlogic.controller;


import com.example.discountlogic.data.PurchaseDetails
import com.example.discountlogic.service.DiscountService
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import javax.validation.Valid

@RestController
@Api(value="Discount")
 class DiscountController(private val discountService: DiscountService) {

        var logger = LoggerFactory.getLogger(DiscountController::class.java)

        @PostMapping("/discountedBillAmount")
        fun yourPostMethod(@Valid @RequestBody requestBody: PurchaseDetails): ResponseEntity<BigDecimal> {
            logger.info("DiscountController :: Request received to calculate final bill amount :: Started")
            val response = discountService.calculateFinalAmount(requestBody)
            logger.info("DiscountController :: Request received to calculate final bill amount :: Ended")
            return ResponseEntity.ok(response)
        }
}
