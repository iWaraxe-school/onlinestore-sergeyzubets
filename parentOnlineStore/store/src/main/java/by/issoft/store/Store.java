package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

import static by.issoft.store.utilities.StoreConstants.Store.*;

public class Store {
    private static final Locale defaultLocale = DEFAULT_LOCALE;
    private List<Category> categoryList;
    private Locale storeLocale;

    public Store() {
        categoryList = new ArrayList<>();
        storeLocale = defaultLocale;
    }

    public Store(Locale storeLocale) {
        categoryList = new ArrayList<>();
        this.storeLocale = storeLocale;
        setStoreCurrency(storeLocale);
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public Locale getStoreLocale() {
        return storeLocale;
    }

    public Currency getStoreCurrency() {
        return Currency.getInstance(this.getStoreLocale());
    }

    private void setStoreCurrency(Locale storeLocale) {
        Currency.getInstance(storeLocale);
    }

    public void setStoreLocale(Locale storeLocale) {
        this.storeLocale = storeLocale;
        setStoreCurrency(storeLocale);
    }

    public List<Category> getListOfCategories() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        categoryList.forEach(i -> stringBuilder.append(i.printAllCategoriesAsPerLocale(getStoreLocale())));
        return stringBuilder.toString();
    }

    public List<Product> getListOfAllProducts() {
        return categoryList.stream()
                .map(Category::getListOfProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
