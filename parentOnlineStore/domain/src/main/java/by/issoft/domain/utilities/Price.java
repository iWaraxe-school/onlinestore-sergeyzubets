package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;
import java.util.*;

public class Price {

    private static final int MAX_PRODUCT_PRICE_VALUE = 10_000;
    public static final String PRICE_PATTERN = "###,###,###.##";
    private final int price;
    private static final Map<Integer, Price> pricesPool = new HashMap<>();

    private Price(int price) {
        this.price = price;
    }

    public static Price of(int price) {
        //Input validation
        Preconditions.checkArgument(price > 0,"Price must be more than 0 " + price);
        Preconditions.checkArgument(price <= MAX_PRODUCT_PRICE_VALUE,
                "Price must be less than " + MAX_PRODUCT_PRICE_VALUE + ". Current value " + price);

        final Price priceFromPool = pricesPool.get(price);

        if (priceFromPool != null) {
            return priceFromPool;
        }
        final Price newPrice = new Price(price);
        pricesPool.put(price, newPrice);
        return newPrice;
    }

    public int getValue () {
        return price;
    }

    public static int getMaxProductPriceValue() {
        return MAX_PRODUCT_PRICE_VALUE;
    }

    public static String getPricePattern() {
        return PRICE_PATTERN;
    }

    public static String printPricesPool() {
        return pricesPool.keySet().toString();
    }
}
