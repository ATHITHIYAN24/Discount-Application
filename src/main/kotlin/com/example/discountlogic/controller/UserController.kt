package com.example.discountlogic.controller;


import com.example.discountlogic.data.UserData
import com.example.discountlogic.model.UserDetails
import com.example.discountlogic.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.text.ParseException
import javax.validation.Valid

@RestController
@Api(value = "UserDetails")
class UserController(private val userService: UserService) {

    var logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/user")
    @ApiOperation(value = "Add User", response = UserDetails::class)
    @Throws(ParseException::class)
    fun saveUserDetails(@Valid @RequestBody userData: UserData): ResponseEntity<UserDetails> {
        logger.info("UserController :: Request received to save the user details  :: Started")
        val response = userService.saveUserDetails(userData)
        logger.info("UserController :: Request received to save the user details  :: Ended")
        return ResponseEntity.ok(response);
    }
}