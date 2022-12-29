package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.utilities.CategoryType;

public class BikeCategory extends Category {

    private static final String NAME = CategoryType.BIKE.name();
    private static BikeCategory instance;

    private BikeCategory() {
        super(NAME);
    }

    public static BikeCategory getInstance() {
        if (instance == null) {
            synchronized (BikeCategory.class) {
                if (instance == null) {
                    instance = new BikeCategory();
                }
            }
        }
        return instance;
    }
}