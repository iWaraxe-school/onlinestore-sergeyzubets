package by.issoft.domain.categories;

import by.issoft.domain.Category;
import by.issoft.domain.utilities.CategoryType;

public class PhoneCategory extends Category {

    private static final String NAME = CategoryType.PHONE.name();
    private static PhoneCategory instance;

    private PhoneCategory() {
        super(NAME);
    }

    public static PhoneCategory getInstance() {
        if (instance == null) {
            synchronized (PhoneCategory.class) {
                if (instance == null) {
                    instance = new PhoneCategory();
                }
            }
        }
        return instance;
    }
}