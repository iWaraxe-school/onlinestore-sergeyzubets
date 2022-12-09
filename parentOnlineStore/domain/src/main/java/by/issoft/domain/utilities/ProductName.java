package by.issoft.domain.utilities;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

import static by.issoft.store.utilities.StoreConstants.ProductConstants.NameConstants.*;

public class ProductName {

    public static final int minProductNameLength = MIN_PRODUCT_NAME_LENGTH;
    public static final int maxProductNameLength = MAX_PRODUCT_NAME_LENGTH;
    public static final String productNameIsEmptyErrorMessage = NAME_IS_EMPTY_ERROR_MESSAGE;
    public static final String productNameLengthExceedsMaxErrorMessage = NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE;
    private final String productName;
    private static final Map<String, ProductName> poolOfProductNames = new HashMap<>();

    private ProductName(String productName) {
        this.productName = productName;
    }

    public static ProductName of(String productName) {
        Preconditions.checkArgument(productName.length() > minProductNameLength, productNameIsEmptyErrorMessage);
        Preconditions.checkArgument(productName.length() <= maxProductNameLength, productNameLengthExceedsMaxErrorMessage + productName.length());

        final ProductName nameFromPool = poolOfProductNames.get(productName);

        if (nameFromPool != null) {
            return nameFromPool;
        }
        final ProductName newProductName = new ProductName(productName);
        poolOfProductNames.put(productName, newProductName);
        return newProductName;
    }

    public String getValue() {
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