package com.epam.tamentoring.bo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderService {
    private DiscountUtility discountUtility;

    public double getOrderPrice(UserAccount user) {
        double discount = discountUtility.calculateDiscount(user);
        return user.getShoppingCart().getCartTotalPrice() - discount;
    }
}
