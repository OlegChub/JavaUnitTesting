package com.epam.tamentoring.bo;

import com.epam.tamentoring.exceptions.ProductNotFoundException;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public void addProductToCart(Product productToAdd) {
        int productToAddId = productToAdd.getId();
        if (getProductIds().contains(productToAddId)) {
            double initialProductQuantity = getProductById(productToAddId).getQuantity();
            double productQuantityToAdd = productToAdd.getQuantity();
            getProductById(productToAddId).setQuantity(initialProductQuantity + productQuantityToAdd);
        } else {
            products.add(productToAdd);
        }
    }

    public void removeProductFromCart(Product product) {
        if (getProductIds().contains(product.getId())) {
            products.remove(product);
        } else {
            throw new ProductNotFoundException("Product is not found in the cart: " + product.toString());
        }
    }

    public double getCartTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getQuantity() * product.getPrice();
        }
        return totalPrice;
    }

    private List<Integer> getProductIds() {
        List<Integer> productIds = new ArrayList<>();
        for (Product productInCart : products) {
            productIds.add(productInCart.getId());
        }
        return productIds;
    }

    public Product getProductById(int id) {
        for (Product productInCart : products) {
            if (productInCart.getId() == id) {
                return productInCart;
            }
        }
        throw new ProductNotFoundException(String.format("Product with %s ID is not found in the shopping cart!", id));
    }

}
