package by.issoft.domain;

import java.util.Currency;

public class Product {
    private final String categoryName;
    private final String name;
    private final int rate;
    private final int price;
    private final Currency productPriceCurrency;

    public Product(String name, int rate, int price, String categoryName, Currency productPriceCurrency) {
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.categoryName = categoryName;
        this.productPriceCurrency = productPriceCurrency;
    }

    @Override
    public String toString() {
        return " Category: " + categoryName + " | Product: " + name + " | Rate: " + rate + " | Price, "+ productPriceCurrency + ": " + price;
    }

}