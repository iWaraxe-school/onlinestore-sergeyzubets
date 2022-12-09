package by.issoft.store.utilities;

import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

import static by.issoft.store.utilities.StoreConstants.ProductConstants.NameConstants.*;
import static by.issoft.store.utilities.StoreConstants.ProductConstants.PriceConstants.*;
import static by.issoft.store.utilities.StoreConstants.ProductConstants.RateConstants.*;

public class RandomStorePopulator {

    public static final int maxProductNameLength = MAX_PRODUCT_NAME_LENGTH;
    public static final int productPriceMinValue = MIN_PRODUCT_PRICE_VALUE;
    public static final int productPriceMaxValue = MAX_PRODUCT_PRICE_VALUE;
    public static final int productRateMinValue = MIN_PRODUCT_RATE_VALUE;
    public static final int productRateMaxValue = MAX_PRODUCT_RATE_VALUE;
    Faker faker;

    public RandomStorePopulator(Store store) {
        faker = new Faker(store.getStoreLocale());
    }

    public ProductName generateProductName(String categoryName) {
        ProductName generatedProductName;
        switch (categoryName) {
            case "Bike":
                generatedProductName = ProductName.of(faker.name().fullName());
                //generatedProductName = ProductName.of(faker.animal().name());
                break;
            case "Milk":
                generatedProductName = ProductName.of(faker.animal().name());
                break;
            case "Phone":
                //generatedProductName = ProductName.of(faker.animal().name());
                generatedProductName = ProductName.of(faker.commerce().productName());
                break;
            default:
                generatedProductName = ProductName.of(faker.lorem().fixedString(maxProductNameLength));
        }
        return generatedProductName;
    }

    public Rate generateProductRate() {
        return Rate.of(faker.number().numberBetween(productRateMinValue, productRateMaxValue));
    }

    public Price generateProductPrice() {
        int generatedPrice = faker.random().nextInt(productPriceMaxValue);
        while (generatedPrice <= productPriceMinValue) {
            generatedPrice = faker.random().nextInt(productPriceMaxValue);
        }
        return Price.of(generatedPrice);
    }

}
