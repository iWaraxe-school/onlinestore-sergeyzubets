package by.issoft.domain.utilities;

import by.issoft.domain.Category;
import by.issoft.domain.categories.*;
import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static by.issoft.domain.utilities.DomainConstants.CategoryConstants.*;

public class CategoryFactory {

    public Map<CategoryType, Supplier<Category>> getCategoryMap() {
        Map<CategoryType, Supplier<Category>> categoryTypeMap = new HashMap<>();
        categoryTypeMap.put(CategoryType.BIKE, BikeCategory::getInstance);
        categoryTypeMap.put(CategoryType.MILK, MilkCategory::getInstance);
        categoryTypeMap.put(CategoryType.PHONE, PhoneCategory::getInstance);
        return categoryTypeMap;
    }

    public Category getCategoryByType(CategoryType type) {
        return getCategoryMap().get(type).get();
    }

    public Category getCategoryByCategoryName(String categoryName) {
        checkArgument(getCategoryNameFromEnum(categoryName) != null, CATEGORY_IS_NOT_SPECIFIED_ERROR_MESSAGE);
        return getCategoryMap().get(getCategoryNameFromEnum(categoryName)).get();
    }

    private static CategoryType getCategoryNameFromEnum(String categoryName) {
        for (CategoryType categoryType : CategoryType.values()) {
            if (categoryType.name().equals(categoryName.toUpperCase())) {
                return categoryType;
            }
        }
        return null;
    }
}
