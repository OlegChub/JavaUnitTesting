package com.epam.tamentoring.bo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserAccount {
    private String name;
    private String surname;
    private String dateOfBirth;
    private ShoppingCart shoppingCart;
}
