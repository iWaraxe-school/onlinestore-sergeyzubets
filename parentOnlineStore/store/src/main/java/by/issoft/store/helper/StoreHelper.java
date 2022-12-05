package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.utilities.RandomStorePopulator;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class StoreHelper {

    private static final int RANDOM_MIN = 1;
    private static final int RANDOM_MAX = 10;
    Store store;

    public StoreHelper(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        RandomStorePopulator storePopulator = new RandomStorePopulator(store.getDefaultStoreLocale());
        Map<Category, Integer> categoriesPool = populateCategoriesPool();

        for (Map.Entry<Category, Integer> entry : categoriesPool.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++){
                Product product = new Product(
                        storePopulator.generateProductName(entry.getKey().getCategoryName()),   //Product Name
                        storePopulator.generateProductRate(),                                   //Product Rate
                        storePopulator.generateProductPrice(),                                  //Product Price
                        entry.getKey().getCategoryName(),                                       //Product Category Name
                        store.getDefaultStoreCurrency());                                       //Product Currency
                entry.getKey().addProduct(product);
            }
            store.addCategory(entry.getKey());
        }
    }

    private static Map<Category, Integer> populateCategoriesPool() {
        Map<Category, Integer> setOfCategories = new HashMap<>();
        Reflections reflections = new Reflections ("by.issoft.domain.categories");
        //get all categories
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        //generate random product amount for each category
        for (Class<? extends Category> categoryType : subTypes) {
            try {
                Random randomProductsAmount = new Random(); // random int from RANDOM_MIN to RANDOM_MAX
                setOfCategories.put(categoryType.getConstructor().newInstance(), randomProductsAmount.nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return setOfCategories;
    }
}
