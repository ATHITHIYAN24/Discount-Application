import com.example.discountlogic.data.PurchaseDetails
import com.example.discountlogic.enumclasses.ProductsType
import com.example.discountlogic.enumclasses.UserType
import com.example.discountlogic.exception.UserNotFoundException
import com.example.discountlogic.model.UserDetails
import com.example.discountlogic.repository.UserRepository
import com.example.discountlogic.service.DiscountServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.String
import java.math.BigDecimal
import java.text.SimpleDateFormat
import kotlin.Throws


@ExtendWith(MockitoExtension::class)
class DiscountServiceImplTest {

    @InjectMocks
    private lateinit var discountServiceImpl: DiscountServiceImpl

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userDetails: UserDetails

    private lateinit var purchaseDetails: PurchaseDetails

    @BeforeEach
    fun setup() {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        userDetails = UserDetails("testing", "tesing@gmail.com", "employee", formatter.parse("15-04-2020"))
        purchaseDetails = PurchaseDetails("tesing@gmail.com", BigDecimal.valueOf(950L), String.valueOf(ProductsType.NONGROCERY))
    }

    @Test
    fun calculateFinalAmountGroceryEmployeeTest() {
        Mockito.`when`(userRepository.findByEmail("tesing@gmail.com")).thenReturn(userDetails)
        val finalAmount = discountServiceImpl.calculateFinalAmount(purchaseDetails)
        Assertions.assertEquals(BigDecimal.valueOf(635), finalAmount)
    }

    @Test
    fun calculateFinalAmountGroceryAffiliateTest() {
        userDetails.userType = String.valueOf(UserType.AFFILIATE)
        Mockito.`when`(userRepository.findByEmail("tesing@gmail.com")).thenReturn(userDetails)
        val finalAmount = discountServiceImpl.calculateFinalAmount(purchaseDetails)
        Assertions.assertEquals(BigDecimal.valueOf(815), finalAmount)
    }

    @Test
    fun calculateFinalAmountGroceryCustomerTest() {
        userDetails.userType = String.valueOf(UserType.CUSTOMER)
        Mockito.`when`(userRepository.findByEmail("tesing@gmail.com")).thenReturn(userDetails)
        val finalAmount = discountServiceImpl.calculateFinalAmount(purchaseDetails)
        Assertions.assertEquals(BigDecimal.valueOf(857.5), finalAmount)
    }

    @Test
    @Throws(UserNotFoundException::class)
    fun calculateFinalAmountUserNotFoundExceptionTest() {
        userDetails.userType = String.valueOf(UserType.CUSTOMER)
        Mockito.`when`(userRepository.findByEmail("tesing@gmail.com")).thenReturn(null)
        Assertions.assertThrows(UserNotFoundException::class.java) {
            discountServiceImpl.calculateFinalAmount(
                purchaseDetails
            )
        }
    }
}

