package com.example.discountlogic.exception

import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException
import java.text.ParseException
import javax.validation.ConstraintViolationException
import javax.validation.UnexpectedTypeException

@RestControllerAdvice
class GlobalExceptionHandlerKotlin {

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.constraintViolations.stream().findFirst().get().propertyPath.toString() + ":" + ex.constraintViolations.stream().findFirst().get().message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.fieldErrors.stream().findFirst().get().field + ":" + ex.fieldErrors.stream().findFirst().get().defaultMessage)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body("billAmount must be numeric")
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFountException(ex: UserNotFoundException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(ParseException::class)
    fun handleParseException(ex: ParseException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleSQLIntegrityConstraintViolationException(ex: SQLIntegrityConstraintViolationException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Email Already Exist")
    }

    @ExceptionHandler(UnexpectedTypeException::class)
    fun handleUnexpectedTypeException(ex: UnexpectedTypeException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }

}