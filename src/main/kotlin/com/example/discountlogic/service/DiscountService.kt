package com.example.discountlogic.service;

import com.example.discountlogic.data.PurchaseDetails
import java.math.BigDecimal;


interface DiscountService {
    fun calculateFinalAmount(purchaseDetails: PurchaseDetails): BigDecimal
}
