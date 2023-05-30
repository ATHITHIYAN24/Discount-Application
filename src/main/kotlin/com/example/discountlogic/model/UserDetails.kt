package com.example.discountlogic.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
class UserDetails() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null

    @Column(name = "name")
    @NotEmpty
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must be alphabet")
    var name: String=""

    @Column(name = "email", unique = true)
    @Email
    @NotEmpty
    @NotBlank
    @NotNull
    var email: String =""

    @Column(name = "user_type")
    @NotEmpty
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "UserType must be alphabet")
    var userType: String =""

    @Column(name = "purchase_start_date")
    var purchaseStartDate: Date = Date()

    constructor(
        name: String,
        email: String,
        userType: String,
        purchaseStartDate: Date
    ) : this() {
        this.name = name
        this.email = email
        this.userType = userType
        this.purchaseStartDate = purchaseStartDate
    }
}