package by.issoft.domain;

import by.issoft.domain.utilities.PresentProductsAsTable;
import com.google.common.base.Preconditions;

import java.util.*;

import static by.issoft.domain.utilities.DomainConstants.CategoryConstants.*;

public class Category {

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
        Preconditions.checkArgument(categoryName.length() > MIN_CATEGORY_NAME_LENGTH,NAME_IS_EMPTY_ERROR_MESSAGE);
        Preconditions.checkArgument(categoryName.length() <= MAX_CATEGORY_NAME_LENGTH, NAME_LENGTH_EXCEEDS_MAX_VALUE_ERROR_MESSAGE + categoryName.length());
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    @Override
    public String toString() {
        return '\n' + "Category: " + categoryName + '\n'
                + new PresentProductsAsTable().getProductsAsTable(listOfProducts);
    }
}
