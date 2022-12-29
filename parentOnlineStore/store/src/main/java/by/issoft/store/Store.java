package by.issoft.store;

import by.issoft.domain.*;
import by.issoft.domain.utilities.PresentProductsAsTable;
import by.issoft.store.helper.DatabaseHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

@Slf4j
public class Store {
    private Set<Category> categoryList;
    private Locale storeLocale;
    private static Store instance;
    private List<Product> purchasedGods = new CopyOnWriteArrayList<>();
    public static final String WELCOME_MESSAGE = "Hello! Welcome to the Store. The Store language is "
            + Store.getInstance().getStoreLocale().getDisplayLanguage() + ". The Store currency is "
            + Store.getInstance().getStoreCurrency() + '\n';
    DatabaseHelper databaseHelper = new DatabaseHelper();

    private Store() {
        categoryList = new LinkedHashSet<>();
        storeLocale = DEFAULT_STORE_LOCALE;
    }

    public static Store getInstance() {
        if (instance == null) {
            synchronized (Store.class) {
                if (instance == null) {
                    instance = new Store();
                }
            }
        }
        return instance;
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

    public Set<Category> getListOfCategories() {
        categoryList = new DatabaseHelper().getCategoriesFromDatabase();
        return categoryList;
    }

    public void setCategoryList(Set<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        getListOfCategories().forEach(category -> stringBuilder.append('\n' + "Category: ")
                .append(category.getCategoryName())
                .append('\n')
                .append(new PresentProductsAsTable().getProductsAsTable(
                        databaseHelper.getProductsPerCategoryFromDatabase(category))));
        return stringBuilder.toString();
    }

    public List<Product> getListOfAllProducts() {
        return getListOfCategories().stream()
                .map(category -> databaseHelper.getProductsPerCategoryFromDatabase(category))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return getListOfCategories().stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }

    public Product getRandomProductFromStore() {
        return getListOfAllProducts().get(new Random().nextInt(getListOfAllProducts().size()));
    }

    public void addPurchasedGods(Product product) {
        purchasedGods.add(product);
        databaseHelper.addPurchasedGodsToDatabase(product);
    }

    public List<Product> getAllPurchasedGods() {
        purchasedGods = databaseHelper.getAllPurchasedGodsFromDatabase();
        return purchasedGods;
    }

    public void clearPurchasedGodsCollection() {
        log.info(CART_WAS_CLEANED_UP);
        purchasedGods.clear();
        databaseHelper.removeAllPurchasedGodsFromDatabase();
    }

    public Product getProductByName(String productName) {
        return databaseHelper.getProductByNameFromDatabase(productName);
    }
}
