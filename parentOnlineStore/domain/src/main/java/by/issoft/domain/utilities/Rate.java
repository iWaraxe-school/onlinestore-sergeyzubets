package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

import static by.issoft.store.utilities.StoreConstants.ProductConstants.RateConstants.*;

public class Rate {

    public static final int productRateMinValue = MIN_PRODUCT_RATE_VALUE;
    public static final int productRateMaxValue = MAX_PRODUCT_RATE_VALUE;
    public static final String productRateLessThanMinErrorMessage = RATE_LESS_THAN_MIN_VALUE_ERROR_MESSAGE;
    public static final String productRateExceedsMaxErrorMessage = RATE_EXCEEDS_MAX_VALUE_ERROR_MESSAGE;
    private final int rate;
    private static final Map<Integer, Rate> poolOfRates = new HashMap<>();

    private Rate(int rate) {
        this.rate = rate;
    }

    public static Rate of(int rate) {
        Preconditions.checkArgument(rate >= productRateMinValue, productRateLessThanMinErrorMessage);
        Preconditions.checkArgument(rate <= productRateMaxValue, productRateExceedsMaxErrorMessage + rate);

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