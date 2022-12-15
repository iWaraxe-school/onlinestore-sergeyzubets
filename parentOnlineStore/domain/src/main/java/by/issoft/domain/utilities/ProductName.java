package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

import static by.issoft.store.utilities.StoreConstants.ProductConstants.NameConstants.*;

public class ProductName {

    private final String productName;
    private static final Map<String, ProductName> poolOfProductNames = new HashMap<>();

    private ProductName(String productName) {
        this.productName = productName;
    }

    public static ProductName of(String productName) {
        Preconditions.checkArgument(productName.length() > MIN_PRODUCT_NAME_LENGTH, NAME_IS_EMPTY_ERROR_MESSAGE);
        Preconditions.checkArgument(productName.length() <= MAX_PRODUCT_NAME_LENGTH, NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE + productName.length());

        final ProductName nameFromPool = poolOfProductNames.get(productName);

        if (nameFromPool != null) {
            return nameFromPool;
        }
        final ProductName newProductName = new ProductName(productName);
        poolOfProductNames.put(productName, newProductName);
        return newProductName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return this.productName;
    }

    public static String printProductNamesPool() {
        return poolOfProductNames.keySet().toString();
    }
}