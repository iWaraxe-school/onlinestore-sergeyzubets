package by.issoft.domain.utilities;

import by.issoft.domain.Category;
import by.issoft.domain.categories.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CategoryFactory {

    public Map<CategoryType, Supplier<Category>> getCategoryMap() {
        Map<CategoryType, Supplier<Category>> categoryTypeMap = new HashMap<>();
        categoryTypeMap.put(CategoryType.BIKE, BikeCategory::getInstance);
        categoryTypeMap.put(CategoryType.MILK, MilkCategory::getInstance);
        categoryTypeMap.put(CategoryType.PHONE, PhoneCategory::getInstance);
        return categoryTypeMap;
    }

    public Category getCategory(CategoryType type) {
        return getCategoryMap().get(type).get();
    }

}
