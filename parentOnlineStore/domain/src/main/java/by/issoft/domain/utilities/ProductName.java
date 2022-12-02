package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

public class ProductName {

    private static final int MAX_PRODUCT_NAME_LENGTH = 50;
    private final String productName;
    private static final Map<String, ProductName> productNamesPool = new HashMap<>();

    private ProductName(String productName) {
        this.productName = productName;
    }

    public static ProductName of(String productName) {
        //Input validation
        Preconditions.checkArgument(!productName.equals(""),"Product Name cannot be empty");
        Preconditions.checkArgument(productName.length() <= MAX_PRODUCT_NAME_LENGTH,
                "The length of  Product Name must be "+ MAX_PRODUCT_NAME_LENGTH
                        + " characters or fewer. You entered " + productName.length() + " characters.");

        final ProductName rateFromPool = productNamesPool.get(productName);

        if (rateFromPool != null) {
            return rateFromPool;
        }
        final ProductName newProductName = new ProductName(productName);
        productNamesPool.put(productName, newProductName);
        return newProductName;
    }

    @Override
    public String toString() {
        return this.productName;
    }

    public static int getMaxProductNameLength() {
        return MAX_PRODUCT_NAME_LENGTH;
    }

    public static String printProductNamesPool() {
        return productNamesPool.keySet().toString();
    }
}