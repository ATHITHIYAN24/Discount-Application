package com.example.discountlogic.controller

import com.example.discountlogic.data.UserData
import com.example.discountlogic.model.UserDetails
import com.example.discountlogic.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user")
@Api(value = "UserDetails")
class UserController(private val userService: UserService) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping
    @ApiOperation(value = "Add User", response = UserDetails::class)
    fun saveUserDetails(@Valid @RequestBody userData: UserData): ResponseEntity<UserDetails> {
        logger.info("UserController :: Request received to save the user details :: Started")
        val response: UserDetails
        try {
            response = userService.saveUserDetails(userData)
        } catch (e: Exception) {
            logger.error("UserController :: Error occurred while saving user details: {}", e.message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null) // Or return a custom error message if desired
        }
        logger.info("UserController :: Request received to save the user details :: Ended")
        return ResponseEntity.ok(response)
    }

}
