package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

public class Rate {

    private static final int MAX_PRODUCT_RATE_VALUE = 6;
    private final int rate;
    private static final Map<Integer, Rate> ratesPool = new HashMap<>();

    private Rate(int rate) {
        this.rate = rate;
    }

    public static Rate of(int rate) {
        //Input validation
        Preconditions.checkArgument(rate >= 0,"Rate must be positive " + rate);
        Preconditions.checkArgument(rate <= MAX_PRODUCT_RATE_VALUE,
                "Rate must be less than " + MAX_PRODUCT_RATE_VALUE + ". Current value " + rate);

        final Rate rateFromPool = ratesPool.get(rate);

        if (rateFromPool != null) {
            return rateFromPool;
        }
        final Rate newRate = new Rate(rate);
        ratesPool.put(rate, newRate);
        return newRate;
    }

    public int getValue() {
        return rate;
    }

    public static int getMaxProductRateValue() {
        return MAX_PRODUCT_RATE_VALUE;
    }

    public static String printRatesPool() {
        return ratesPool.keySet().toString();
    }

}