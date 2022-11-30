package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {

    private String categoryName;
    protected List<Product> productsList;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        productsList = new ArrayList<Product>();
    }

    public void addProduct (Product product) {
        productsList.add(product);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public String toString() {
        return "Category name=" + categoryName + ", list of products:" + productsList.toString();
    }
}
