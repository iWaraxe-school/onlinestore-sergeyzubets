package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;
import java.util.*;

import static by.issoft.store.utilities.StoreConstants.ProductConstants.PriceConstants.*;

public class Price {

    private final int price;
    private static final Map<Integer, Price> poolOfPrices = new HashMap<>();

    private Price(int price) {
        this.price = price;
    }

    public static Price of(int price) {
        Preconditions.checkArgument(price > MIN_PRODUCT_PRICE_VALUE, PRICE_LESS_OR_EQUALS_MIN_VALUE_ERROR_MESSAGE);
        Preconditions.checkArgument(price <= MAX_PRODUCT_PRICE_VALUE, PRICE_EXCEEDS_MAX_VALUE_ERROR_MESSAGE + price);

        final Price priceFromPool = poolOfPrices.get(price);

        if (priceFromPool != null) {
            return priceFromPool;
        }
        final Price newPrice = new Price(price);
        poolOfPrices.put(price, newPrice);
        return newPrice;
    }

    public int getValue() {
        return price;
    }

    public static String printPricesPool() {
        return poolOfPrices.keySet().toString();
    }
}
