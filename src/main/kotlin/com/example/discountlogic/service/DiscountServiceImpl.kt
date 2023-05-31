package com.example.discountlogic.service;


import com.example.discountlogic.data.PurchaseDetails
import com.example.discountlogic.enumclasses.ProductsType
import com.example.discountlogic.enumclasses.UserType
import com.example.discountlogic.exception.UserNotFoundException
import com.example.discountlogic.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.String
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import kotlin.Long
import kotlin.Throws

@Service
class DiscountServiceImpl(@Autowired private val userRepository: UserRepository) : DiscountService {

    var logger = LoggerFactory.getLogger(DiscountServiceImpl::class.java)

    @Value("\${spring.data.discountAmountPerHundredDollar}")
    var discountAmountPerHundredDollar: Long = 5

    @Value("\${spring.data.nonGroceryProductDiscountForEmployee}")
    var nonGroceryProductDiscountForEmployee: Long = 30

    @Value("\${spring.data.discountAmountPerHundredDollarForAffiliate}")
    var discountAmountPerHundredDollarForAffiliate: Long = 10

    @Value("\${spring.data.discountAmountPerHundredDollarForCustomer}")
    var discountAmountPerHundredDollarForCustomer: Long = 5

    @Throws(UserNotFoundException::class)
    override fun calculateFinalAmount(purchaseDetails: PurchaseDetails): BigDecimal {
        logger.info("DiscountServiceImpl :: Request received to calculate final bill amount :: Started")
        var billAmount: BigDecimal? = purchaseDetails.billAmount
        val userDetails = userRepository.findByEmail(purchaseDetails.email)
            ?: throw UserNotFoundException("User Not Found")
        val discountAmountPerHundredDollar = BigDecimal(discountAmountPerHundredDollar)
        if (!purchaseDetails.productsType.equals(String.valueOf(ProductsType.GROCERY),ignoreCase = true)) {
            if (userDetails.userType.equals(String.valueOf(UserType.EMPLOYEE),ignoreCase = true)) {
                billAmount = nonGroceryProductDiscount(billAmount!!, BigDecimal.valueOf(nonGroceryProductDiscountForEmployee))
            } else if (userDetails.userType.equals(String.valueOf(UserType.AFFILIATE),ignoreCase = true)) {
                billAmount = nonGroceryProductDiscount(billAmount!!, BigDecimal.valueOf(discountAmountPerHundredDollarForAffiliate))
            } else if (userDetails.userType.equals(String.valueOf(UserType.CUSTOMER),ignoreCase = true)) {
                val noOfYears: Long = userDetails.purchaseStartDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate().until(LocalDate.now(), ChronoUnit.YEARS)
                if (noOfYears >= 2) {
                    billAmount = nonGroceryProductDiscount(billAmount!!, BigDecimal.valueOf(discountAmountPerHundredDollarForCustomer))
                }
            }
        }
        val commonDiscountAmount = billAmount!!.divide(BigDecimal.valueOf(100L), RoundingMode.FLOOR)
            .multiply(discountAmountPerHundredDollar)
        logger.info("DiscountServiceImpl :: Request received to calculate final bill amount :: Ended")
        return billAmount.subtract(commonDiscountAmount)
    }

    private fun nonGroceryProductDiscount(billAmount: BigDecimal, percentage: BigDecimal): BigDecimal? {
        logger.info("DiscountServiceImpl :: Request received to calculate non-Grocery product discount amount :: Started")
        val discountAmount = billAmount.multiply(percentage).divide(BigDecimal.valueOf(100L))
        logger.info("DiscountServiceImpl :: Request received to calculate non-Grocery product discount amount :: Ended")
        return billAmount.subtract(discountAmount)
    }
}