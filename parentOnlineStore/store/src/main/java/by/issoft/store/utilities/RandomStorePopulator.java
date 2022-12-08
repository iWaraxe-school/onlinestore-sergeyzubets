package by.issoft.store.utilities;

import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    public static final int maxProductNameLength = StoreConstants.ProductConstants.NameConstants.MAX_PRODUCT_NAME_LENGTH;
    public static final int productPriceMinValue = StoreConstants.ProductConstants.PriceConstants.MIN_PRODUCT_PRICE_VALUE;
    public static final int productPriceMaxValue = StoreConstants.ProductConstants.PriceConstants.MAX_PRODUCT_PRICE_VALUE;
    public static final int productRateMinValue = StoreConstants.ProductConstants.RateConstants.MIN_PRODUCT_RATE_VALUE;
    public static final int productRateMaxValue = StoreConstants.ProductConstants.RateConstants.MAX_PRODUCT_RATE_VALUE;
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
