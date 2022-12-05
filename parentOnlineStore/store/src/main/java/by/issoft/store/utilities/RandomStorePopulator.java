package by.issoft.store.utilities;

import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import com.github.javafaker.Faker;
import java.util.*;
import static by.issoft.domain.utilities.Price.getMaxProductPriceValue;
import static by.issoft.domain.utilities.ProductName.getMaxProductNameLength;
import static by.issoft.domain.utilities.Rate.getMaxProductRateValue;

public class RandomStorePopulator {

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
                generatedProductName = ProductName.of(faker.lorem().fixedString(getMaxProductNameLength()));
        }
        return generatedProductName;
    }

    public Rate generateProductRate () {
        return Rate.of(faker.random().nextInt(getMaxProductRateValue()));
    }

    public Price generateProductPrice () {
        Price generatedPrice = Price.of(faker.random().nextInt(getMaxProductPriceValue()));
        //faker validation: price cannot be <= 0
        while (generatedPrice.getValue() <= 0) {
            generatedPrice = Price.of(faker.random().nextInt(getMaxProductPriceValue()));
        }
        return generatedPrice;
    }

}
