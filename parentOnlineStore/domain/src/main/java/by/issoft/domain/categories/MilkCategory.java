package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.utilities.CategoryType;

public class MilkCategory extends Category {

    private static final String NAME = CategoryType.MILK.name();
    private static MilkCategory instance;

    private MilkCategory() {
        super(NAME);
    }

    public static MilkCategory getInstance() {
        if (instance == null) {
            synchronized (MilkCategory.class) {
                if (instance == null) {
                    instance = new MilkCategory();
                }
            }
        }
        return instance;
    }

}
