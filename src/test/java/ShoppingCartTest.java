import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private static final int PRODUCT1_ID = 1;
    private static final String PRODUCT1_NAME = "Product #1";
    private static final double PRODUCT1_PRICE = 100;
    private static final double PRODUCT1_QUANTITY = 1;

    private static final int PRODUCT2_ID = 2;
    private static final String PRODUCT2_NAME = "Product #2";
    private static final double PRODUCT2_PRICE = 200;
    private static final double PRODUCT2_QUANTITY = 2;

    private final Product PRODUCT1 = new Product(PRODUCT1_ID, PRODUCT1_NAME, PRODUCT1_PRICE, PRODUCT1_QUANTITY);
    private final Product PRODUCT2 = new Product(PRODUCT2_ID, PRODUCT2_NAME, PRODUCT2_PRICE, PRODUCT2_QUANTITY);

    private static final double PRODUCT1_SUM_IN_CART = PRODUCT1_PRICE * PRODUCT1_QUANTITY;
    private static final double PRODUCT2_SUM_IN_CART = PRODUCT2_PRICE * PRODUCT2_QUANTITY;


    @Test
    void addProductWithoutParamsToCart() {

        var product = new Product();
        List<Product> productsList = new ArrayList<>();

        var shoppingCart = new ShoppingCart(productsList);
        shoppingCart.addProductToCart(product);

        assertEquals(1, productsList.size());
        assertEquals(0, productsList.get(0).getId());
        assertNull(productsList.get(0).getName());
        assertEquals(0, productsList.get(0).getPrice());
        assertEquals(0, productsList.get(0).getQuantity());
    }

    @Test
    void addProductWithAllParametersToCart() {

        List<Product> productsList = new ArrayList<>();

        var shoppingCart = new ShoppingCart(productsList);
        shoppingCart.addProductToCart(PRODUCT1);

        assertEquals(1, productsList.size());
        assertEquals(PRODUCT1_ID, productsList.get(0).getId());
        assertEquals(PRODUCT1_NAME, productsList.get(0).getName());
        assertEquals(PRODUCT1_PRICE, productsList.get(0).getPrice());
        assertEquals(PRODUCT1_QUANTITY, productsList.get(0).getQuantity());
    }

    @Test
    void removeOneExistingProductFromCart() {

        List<Product> productsList = new ArrayList<>();

        var shoppingCart = new ShoppingCart(productsList);
        shoppingCart.addProductToCart(PRODUCT1);

        shoppingCart.removeProductFromCart(PRODUCT1);

        assertEquals(0, productsList.size());
    }

    @Test
    void removeOneExistingProductInQuantityTwoFromCart() {

        List<Product> productsList = new ArrayList<>();

        var shoppingCart = new ShoppingCart(productsList);
        shoppingCart.addProductToCart(PRODUCT2);

        shoppingCart.removeProductFromCart(PRODUCT2);

        assertEquals(0, productsList.size());
    }

    @Test
    void removeNotAddedProductFromCart() {

        List<Product> productsList = new ArrayList<>();
        var shoppingCart = new ShoppingCart(productsList);

        assertThrows(ProductNotFoundException.class, () -> shoppingCart.removeProductFromCart(PRODUCT1));
    }

    @Test
    void removeOneFromTwoAddedProductsFromCart() {

        List<Product> productsList = new ArrayList<>();
        productsList.add(PRODUCT1);
        productsList.add(PRODUCT2);

        var shoppingCart = new ShoppingCart(productsList);
        shoppingCart.removeProductFromCart(PRODUCT1);

        assertEquals(1, productsList.size());
        assertEquals(PRODUCT2_ID, productsList.get(0).getId());
        assertEquals(PRODUCT2_NAME, productsList.get(0).getName());
        assertEquals(PRODUCT2_PRICE, productsList.get(0).getPrice());
        assertEquals(PRODUCT2_QUANTITY, productsList.get(0).getQuantity());
    }

    @Test
    void removeAddedWithoutParamsProductFromCart() {

        var product = new Product();
        List<Product> productsList = new ArrayList<>();
        productsList.add(product);

        var shoppingCart = new ShoppingCart(productsList);
        shoppingCart.removeProductFromCart(product);

        assertEquals(0, productsList.size());
    }

    @Test
    void getCartTotalPriceWithOneProductWithoutParamsInIt() {

        var product = new Product();
        List<Product> productsList = new ArrayList<>();
        productsList.add(product);

        var shoppingCart = new ShoppingCart(productsList);

        assertEquals(0, shoppingCart.getCartTotalPrice());
    }

    @Test
    void getCartTotalPriceWithOneProductInIt() {

        List<Product> productsList = new ArrayList<>();
        productsList.add(PRODUCT1);

        var shoppingCart = new ShoppingCart(productsList);

        assertEquals(PRODUCT1_SUM_IN_CART, shoppingCart.getCartTotalPrice());
    }

    @Test
    void getCartTotalPriceWithOneProductOfQuantityTwoInIt() {

        List<Product> productsList = new ArrayList<>();
        productsList.add(PRODUCT2);

        var shoppingCart = new ShoppingCart(productsList);

        assertEquals(PRODUCT2_SUM_IN_CART, shoppingCart.getCartTotalPrice());
    }

    @Test
    void getCartTotalPriceWithTwoProductsInIt() {

        List<Product> productsList = new ArrayList<>();
        productsList.add(PRODUCT1);
        productsList.add(PRODUCT2);

        var shoppingCart = new ShoppingCart(productsList);

        assertEquals(PRODUCT1_SUM_IN_CART + PRODUCT2_SUM_IN_CART, shoppingCart.getCartTotalPrice());
    }

    @Test
    void getProductByIdFromCartWithOneProduct() {

        List<Product> productsList = new ArrayList<>();
        productsList.add(PRODUCT1);

        var shoppingCart = new ShoppingCart(productsList);

        assertEquals(PRODUCT1, shoppingCart.getProductById(1));
    }

    @Test
    void getProductByIdFromCartWithTwoProducts() {

        List<Product> productsList = new ArrayList<>();
        productsList.add(PRODUCT1);
        productsList.add(PRODUCT2);

        var shoppingCart = new ShoppingCart(productsList);

        assertEquals(PRODUCT2, shoppingCart.getProductById(2));
    }

    @Test
    void getProductByIdFromEmptyCart() {

        List<Product> productsList = new ArrayList<>();
        var shoppingCart = new ShoppingCart(productsList);

        assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(1));
    }

}