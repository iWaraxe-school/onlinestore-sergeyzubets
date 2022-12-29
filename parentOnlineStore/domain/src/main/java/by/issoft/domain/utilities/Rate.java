package by.issoft.domain.utilities;

import static com.google.common.base.Preconditions.*;
import java.util.HashMap;
import java.util.Map;

import static by.issoft.domain.utilities.DomainConstants.ProductConstants.RateConstants.*;

public class Rate {

    private final int rate;
    private static final Map<Integer, Rate> poolOfRates = new HashMap<>();

    private Rate(int rate) {
        this.rate = rate;
    }

    public static Rate of(int rate) {
        checkArgument(rate >= MIN_PRODUCT_RATE_VALUE, RATE_LESS_THAN_MIN_VALUE_ERROR_MESSAGE);
        checkArgument(rate <= MAX_PRODUCT_RATE_VALUE, RATE_EXCEEDS_MAX_VALUE_ERROR_MESSAGE + rate);
        final Rate rateFromPool = poolOfRates.get(rate);
        if (rateFromPool != null) {
            return rateFromPool;
        }
        final Rate newRate = new Rate(rate);
        poolOfRates.put(rate, newRate);
        return newRate;
    }

    public int getValue() {
        return rate;
    }

    public static String printRatesPool() {
        return poolOfRates.keySet().toString();
    }
}