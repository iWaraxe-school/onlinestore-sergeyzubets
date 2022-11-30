package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.*;

public class Store {
    private List<Category> categoryList;
    private Locale defaultStoreLocale = Locale.US;
    private Currency defaultStoreCurrency = Currency.getInstance(defaultStoreLocale);

    public Store() {
        categoryList = new ArrayList<Category>();
    }

    public void addCategory (Category category) {
        categoryList.add(category);
    }

    public List<Category> getCategories() {
        return categoryList;
    }

    public Locale getDefaultStoreLocale() {
        return defaultStoreLocale;
    }

    public Currency getDefaultStoreCurrency() {
        return defaultStoreCurrency;
    }

    public void setDefaultStoreCurrency(Currency defaultStoreCurrency) {
        this.defaultStoreCurrency = defaultStoreCurrency;
    }

    public void setDefaultStoreLocale(Locale defaultStoreLocale) {
        this.defaultStoreLocale = defaultStoreLocale;
        setDefaultStoreCurrency(defaultStoreLocale);
    }

    private Currency setDefaultStoreCurrency (Locale defaultStoreLocale) {
        return defaultStoreCurrency = Currency.getInstance(this.defaultStoreLocale);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void printAllProductsAsPerCategory() {
        for (Category category : categoryList){
            List<Product> productList = category.getProductsList();
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

}
