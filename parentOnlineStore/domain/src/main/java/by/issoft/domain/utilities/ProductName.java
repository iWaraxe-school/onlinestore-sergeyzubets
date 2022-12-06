package by.issoft.domain.utilities;

import by.issoft.store.utilities.StoreConstants;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

public class ProductName {

    public static final int minProductNameLength = StoreConstants.ProductConstants.NameConstants.MIN_PRODUCT_NAME_LENGTH;
    public static final int maxProductNameLength = StoreConstants.ProductConstants.NameConstants.MAX_PRODUCT_NAME_LENGTH;
    public static final String productNameIsEmptyErrorMessage = StoreConstants.ProductConstants.NameConstants.NAME_IS_EMPTY_ERROR_MESSAGE;
    public static final String productNameLengthExceedsMaxErrorMessage = StoreConstants.ProductConstants.NameConstants.NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE;
    private final String productName;
    private static final Map<String, ProductName> poolOfProductNames = new HashMap<>();

    private ProductName(String productName) {
        this.productName = productName;
    }

    public static ProductName of(String productName) {
        //Input validation
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

    @Override
    public String toString() {
        return this.productName;
    }

    public static String printProductNamesPool() {
        return poolOfProductNames.keySet().toString();
    }
}