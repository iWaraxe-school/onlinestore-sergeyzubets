package by.issoft.store.utilities;

import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import com.github.javafaker.Faker;
import java.util.*;

public class RandomStorePopulator {

    public static final int maxProductNameLength = StoreConstants.ProductConstants.NameConstants.MAX_PRODUCT_NAME_LENGTH;
    public static final int productPriceMinValue = StoreConstants.ProductConstants.PriceConstants.MIN_PRODUCT_PRICE_VALUE;
    public static final int productPriceMaxValue = StoreConstants.ProductConstants.PriceConstants.MAX_PRODUCT_PRICE_VALUE;
    public static final int productRateMaxValue = StoreConstants.ProductConstants.RateConstants.MAX_PRODUCT_RATE_VALUE;
    Faker faker;

    public RandomStorePopulator(Locale defaultStoreLocale) {
        faker = new Faker(defaultStoreLocale);
    }

    public ProductName generateProductName (String categoryName) {
        ProductName generatedProductName;
        switch(categoryName) {
            case "BikeCategory":
                generatedProductName = ProductName.of(faker.music().genre());
                break;
            case "MilkCategory":
                generatedProductName = ProductName.of(faker.animal().name());
                break;
            case "PhoneCategory":
                generatedProductName = ProductName.of(faker.commerce().productName());
                break;
            default:
                generatedProductName = ProductName.of(faker.lorem().fixedString(maxProductNameLength));
        }
        return generatedProductName;
    }

    public Rate generateProductRate () {
        return Rate.of(faker.random().nextInt(productRateMaxValue));
    }

    public Price generateProductPrice () {
        Price generatedPrice = Price.of(faker.random().nextInt(productPriceMaxValue));
        //faker validation: price cannot be = 0
        while (generatedPrice.getValue() <= productPriceMinValue) {
            generatedPrice = Price.of(faker.random().nextInt(productPriceMaxValue));
        }
        return generatedPrice;
    }

}
