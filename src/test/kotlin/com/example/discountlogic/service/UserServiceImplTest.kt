package com.example.discountlogic.service

import com.example.discountlogic.data.UserData
import com.example.discountlogic.enumclasses.UserType
import com.example.discountlogic.exception.UserNotFoundException
import com.example.discountlogic.model.UserDetails
import com.example.discountlogic.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.String
import java.text.SimpleDateFormat
import javax.validation.UnexpectedTypeException

@ExtendWith(MockitoExtension::class)
class UserServiceImplTest {

    @InjectMocks
    private lateinit var userServiceImpl: UserServiceImpl

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var userData: UserData
    private lateinit var userDetails: UserDetails

    @BeforeEach
    fun setup() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        userData = UserData("testing", "tesing@gmail.com", "employee", "2020-04-15")
        userDetails = UserDetails("testing", "tesing@gmail.com", "employee", formatter.parse("2020-04-15"))
    }

    @Test
    fun saveUserDetails() {
        Mockito.`when`(userRepository.save(Mockito.any(UserDetails::class.java))).thenReturn(userDetails)
        val userDetailsTest = userServiceImpl.saveUserDetails(userData)
        Assertions.assertNotNull(userDetailsTest)
    }

    @Test
    @Throws(UnexpectedTypeException::class)
    fun calculateFinalAmountUserNotFoundExceptionTest() {
        userData = UserData("testing", "tesing@gmail.com", "employee", "20s20-04-15")
        Assertions.assertThrows(UnexpectedTypeException::class.java) {
            userServiceImpl.saveUserDetails(
                userData
            )
        }
    }
}
