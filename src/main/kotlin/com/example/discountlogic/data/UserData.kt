package com.example.discountlogic.data;

import com.sun.istack.NotNull
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Getter
@Setter
@AllArgsConstructor
data class UserData (

    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val name: String,

    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val userType: String,

    @field:NotNull
    @field:NotEmpty
    @field:NotBlank
    val purchaseStartDate: String

)
