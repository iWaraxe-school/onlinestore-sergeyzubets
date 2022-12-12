package by.issoft.domain;

import by.issoft.domain.utilities.PresentProductsAsTable;
import com.google.common.base.Preconditions;

import java.util.*;

import static by.issoft.store.utilities.StoreConstants.CategoryConstants.*;

public abstract class Category {

    private static final int minCategoryLength = MIN_CATEGORY_NAME_LENGTH;
    private static final int maxCategoryLength = MAX_CATEGORY_NAME_LENGTH;
    private static final String categoryNameIsEmptyErrorMessage = NAME_IS_EMPTY_ERROR_MESSAGE;
    private static final String categoryNameExceedsMaximumErrorMessage = NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE;
    private String categoryName;
    protected List<Product> listOfProducts;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        listOfProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        Preconditions.checkArgument(categoryName.length() > minCategoryLength, categoryNameIsEmptyErrorMessage);
        Preconditions.checkArgument(categoryName.length() <= maxCategoryLength, categoryNameExceedsMaximumErrorMessage + categoryName.length());
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public String printAllCategoriesAsPerLocale(Locale locale) {
        return '\n' + "Category: " + categoryName + '\n'
                + new PresentProductsAsTable().getProductsAsTableAsPerLocale(listOfProducts, locale);
    }

    @Override
    public String toString() {
        return '\n' + "Category: " + categoryName + '\n'
                + new PresentProductsAsTable().getProductsAsTable(listOfProducts);
    }

}
