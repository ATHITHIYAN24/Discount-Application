package com.example.discountlogic.data;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import javax.validation.constraints.Pattern

data class PurchaseDetails(
        @field:NotNull
        @field:NotEmpty
        @field:NotBlank
        @field:Email
        var email: String,
        @field:NotNull
        var billAmount: BigDecimal,
        @field:NotNull
        @field:NotEmpty
        @field:NotBlank
        @field:Pattern(regexp = "^[a-zA-Z]+$", message = "productsType must be alphabet")
        var productsType: String
        )


