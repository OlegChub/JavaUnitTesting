package com.epam.tamentoring.bo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Product {
    private int id;
    private String name;
    private double price;
    private double quantity;
}
