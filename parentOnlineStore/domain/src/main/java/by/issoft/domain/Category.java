package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {

    private String name;
    private List<Product> productsList;

    public Category(String name) {
        this.name = name;
        productsList = new ArrayList<Product>();
    }

    public void addProduct (Product product) {
        productsList.add(product);
    }
}
