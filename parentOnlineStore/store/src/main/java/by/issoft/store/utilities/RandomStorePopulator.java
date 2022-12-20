package by.issoft.store.utilities;

import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

import static by.issoft.domain.utilities.DomainConstants.ProductConstants.NameConstants.*;
import static by.issoft.domain.utilities.DomainConstants.ProductConstants.PriceConstants.*;
import static by.issoft.domain.utilities.DomainConstants.ProductConstants.RateConstants.*;

public class RandomStorePopulator {

    Faker faker;

    public RandomStorePopulator() {
        faker = new Faker(Store.getInstance().getStoreLocale());
    }

    public ProductName generateProductName(String categoryName) {
        ProductName generatedProductName;
        switch (categoryName) {
            case "BIKE":
                generatedProductName = ProductName.of(faker.name().fullName());
                //generatedProductName = ProductName.of(faker.animal().name());
                break;
            case "MILK":
                generatedProductName = ProductName.of(faker.animal().name());
                break;
            case "PHONE":
                //generatedProductName = ProductName.of(faker.animal().name());
                generatedProductName = ProductName.of(faker.commerce().productName());
                break;
            default:
                generatedProductName = ProductName.of(faker.lorem().fixedString(MAX_PRODUCT_NAME_LENGTH));
        }
        return generatedProductName;
    }

    public Rate generateProductRate() {
        return Rate.of(faker.number().numberBetween(MIN_PRODUCT_RATE_VALUE, MAX_PRODUCT_RATE_VALUE));
    }

    public Price generateProductPrice() {
        int generatedPrice = faker.random().nextInt(MAX_PRODUCT_PRICE_VALUE);
        while (generatedPrice <= MIN_PRODUCT_PRICE_VALUE) {
            generatedPrice = faker.random().nextInt(MAX_PRODUCT_PRICE_VALUE);
        }
        return Price.of(generatedPrice);
    }

}
