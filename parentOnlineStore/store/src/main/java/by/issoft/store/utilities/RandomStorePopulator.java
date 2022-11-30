package by.issoft.store.utilities;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomStorePopulator {

    private static final int MAX_GENERATED_PRODUCT_NAME_LENGHT = 10;
    private static final int MAX_GENERATED_PRODUCT_RATE_AMOUNT = 100;
    private static final int MAX_GENERATED_PRODUCT_PRICE_AMOUNT = 10_000;
    Faker faker;

    public RandomStorePopulator(Locale defaultStoreLocale) {
        faker = new Faker(defaultStoreLocale);
    }

    public String generateProductName (String categoryName) {
        String generatedProductName;
        switch(categoryName) {
            case "BikeCategory":
                generatedProductName = faker.music().genre();
                break;
            case "MilkCategory":
                generatedProductName = faker.animal().name();
                break;
            case "PhoneCategory":
                generatedProductName = faker.commerce().productName();
                break;
            default:
                generatedProductName = faker.lorem().fixedString(MAX_GENERATED_PRODUCT_NAME_LENGHT);
        }
        return generatedProductName;
    }

    public int generateProductRate () {
        return this.faker.random().nextInt(MAX_GENERATED_PRODUCT_RATE_AMOUNT);
    }

    public int generateProductPrice () {
        return this.faker.random().nextInt(MAX_GENERATED_PRODUCT_PRICE_AMOUNT);
    }

}
