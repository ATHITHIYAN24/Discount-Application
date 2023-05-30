package com.example.discountlogic.service

import com.example.discountlogic.data.UserData
import com.example.discountlogic.model.UserDetails
import com.example.discountlogic.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.ParseException
import java.text.SimpleDateFormat
import javax.validation.UnexpectedTypeException

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) : UserService {

    var logger: Logger? = LoggerFactory.getLogger(UserServiceImpl::class.java)

    @Throws(ParseException::class)
    override fun saveUserDetails(userData: UserData): UserDetails {
        logger?.info("UserServiceImpl :: Request received to save the user details :: Started")
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        if(!isValidDate(userData.purchaseStartDate)) {
            throw UnexpectedTypeException("Invalid date format. Use yyyy-MM-dd")
        }
        val userDetails = UserDetails(
            userData.name,
            userData.email,
            userData.userType,
            formatter.parse(userData.purchaseStartDate)
        )
        val response = userRepository!!.save(userDetails)
        logger?.info("UserServiceImpl :: Request received to save the user details :: Ended")
        return response
    }

    fun isValidDate(dateString: String): Boolean {
        val regexPattern = """^\d{4}-\d{2}-\d{2}$""".toRegex()
        return regexPattern.matches(dateString)
    }
}