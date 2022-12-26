package by.issoft.domain.utilities;

public interface DomainConstants {

    interface ProductConstants {
        interface NameConstants {
            int MIN_PRODUCT_NAME_LENGTH = 0;
            int MAX_PRODUCT_NAME_LENGTH = 50;
            String NAME_IS_EMPTY_ERROR_MESSAGE = "Product Name cannot be empty";
            String NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE = "The length of Product Name must be equal or less than " + MAX_PRODUCT_NAME_LENGTH + ". Current length value = ";
        }

        interface RateConstants {
            int MIN_PRODUCT_RATE_VALUE = 0;
            int MAX_PRODUCT_RATE_VALUE = 100;
            String RATE_LESS_THAN_MIN_VALUE_ERROR_MESSAGE = "Rate must be more than " + MIN_PRODUCT_RATE_VALUE;
            String RATE_EXCEEDS_MAX_VALUE_ERROR_MESSAGE = "Rate must be equal or less than " + MAX_PRODUCT_RATE_VALUE + ". Current value ";
        }

        interface PriceConstants {
            int MIN_PRODUCT_PRICE_VALUE = 0;
            int MAX_PRODUCT_PRICE_VALUE = 2_000;
            String PRICE_PRINT_PATTERN = "###,###,###,###.##";
            String PRICE_LESS_OR_EQUALS_MIN_VALUE_ERROR_MESSAGE = "Price must be more than " + MIN_PRODUCT_PRICE_VALUE;
            String PRICE_EXCEEDS_MAX_VALUE_ERROR_MESSAGE = "Price must be equal or less than " + MAX_PRODUCT_PRICE_VALUE + ". Current value ";
        }
    }

    interface CategoryConstants {
        int MIN_CATEGORY_NAME_LENGTH = 0;
        int MAX_CATEGORY_NAME_LENGTH = 100;
        String NAME_IS_EMPTY_ERROR_MESSAGE = "Category Name cannot be empty";
        String NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE = "The length of Product Name must be equal or less than " + MAX_CATEGORY_NAME_LENGTH + ". Current length value = ";
        String CATEGORY_DOES_NOT_SPECIFIED_ERROR_MESSAGE = "CategoryType Enum and CategoryFactory do not contain the Category";
    }

}