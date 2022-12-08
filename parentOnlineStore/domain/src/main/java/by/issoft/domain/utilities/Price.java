package by.issoft.domain.utilities;

import by.issoft.store.utilities.StoreConstants;
import com.google.common.base.Preconditions;
import java.util.*;

public class Price {

    public static final int productPriceMinValue = StoreConstants.ProductConstants.PriceConstants.MIN_PRODUCT_PRICE_VALUE;
    public static final int productPriceMaxValue = StoreConstants.ProductConstants.PriceConstants.MAX_PRODUCT_PRICE_VALUE;
    public static final String productPriceIsEmptyErrorMessage = StoreConstants.ProductConstants.PriceConstants.PRICE_LESS_OR_EQUALS_MIN_VALUE_ERROR_MESSAGE;
    public static final String productPriceExceedsMaxErrorMessage = StoreConstants.ProductConstants.PriceConstants.PRICE_EXCEEDS_MAX_VALUE_ERROR_MESSAGE;
    private final int price;
    private static final Map<Integer, Price> poolOfPrices = new HashMap<>();

    private Price(int price) {
        this.price = price;
    }

    public static Price of(int price) {
        //Input validation
        Preconditions.checkArgument(price > productPriceMinValue, productPriceIsEmptyErrorMessage);
        Preconditions.checkArgument(price <= productPriceMaxValue, productPriceExceedsMaxErrorMessage + price);

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
