import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTestWithMock {

    private final static String USER_NAME = "John";
    private final static String USER_SURNAME = "Smith";
    private final static String USER_DATE_OF_BIRTH = "1990/10/10";
    private final static double DISCOUNT = 3.0;
    private final static double SHOPPING_CART_SUM = 200.0;
    private final static double ORDER_PRICE_FINAL = SHOPPING_CART_SUM - DISCOUNT;

    @InjectMocks
    OrderService orderService;

    @Mock
    DiscountUtility discountUtilityMock;

    @Mock
    UserAccount userMock;

    @Mock
    ShoppingCart shoppingCartMock;

    @BeforeEach
    void setUpUser() {
        when(userMock.getName()).thenReturn(USER_NAME);
        when(userMock.getSurname()).thenReturn(USER_SURNAME);
        when(userMock.getDateOfBirth()).thenReturn(USER_DATE_OF_BIRTH);
        when(userMock.getShoppingCart()).thenReturn(shoppingCartMock);
    }

    @Test
    public void getOrderPriceWithDiscountEqualsToThree() {
        when(shoppingCartMock.getCartTotalPrice()).thenReturn(SHOPPING_CART_SUM);

        when(discountUtilityMock.calculateDiscount(userMock)).thenReturn(DISCOUNT);

        assertEquals(USER_NAME, userMock.getName());
        assertEquals(USER_SURNAME, userMock.getSurname());
        assertEquals(USER_DATE_OF_BIRTH, userMock.getDateOfBirth());
        assertEquals(ORDER_PRICE_FINAL, orderService.getOrderPrice(userMock));
    }

    @AfterEach
    void verifyMocks() {
        verify(shoppingCartMock, times(1)).getCartTotalPrice();
        verify(discountUtilityMock, times(1)).calculateDiscount(userMock);

        verifyNoMoreInteractions(userMock);
        verifyNoMoreInteractions(shoppingCartMock);
        verifyNoMoreInteractions(discountUtilityMock);
    }
}
