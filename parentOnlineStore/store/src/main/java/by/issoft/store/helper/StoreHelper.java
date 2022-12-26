package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.utilities.RandomStorePopulator;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static by.issoft.store.utilities.StoreConstants.StorePopulator.*;

public class StoreHelper {

    private static final String METHOD_NAME = "getInstance";

    public StoreHelper() {
    }

    public void populateStoreViaFaker() {
        Map<Category, Integer> poolOfCategories = populatePoolOfCategories();
        for (Map.Entry<Category, Integer> entry : poolOfCategories.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = generateNewProduct(entry.getKey().getCategoryName());
                entry.getKey().addProduct(product);
            }
            Store.getInstance().addCategory(entry.getKey());
        }
    }

    private static Map<Category, Integer> populatePoolOfCategories() {
        Map<Category, Integer> mapOfCategories = new HashMap<>();
        Reflections reflections = new Reflections(CATEGORY_PACKAGE_PATH);
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        subTypes.forEach(subType -> {
            try {
                mapOfCategories.put((Category) subType.getMethod(METHOD_NAME).invoke(subType), getRandomProductsCount());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        return mapOfCategories;
    }

    public Product generateNewProduct(String categoryName) {
        RandomStorePopulator storePopulator = new RandomStorePopulator();
        return new Product(
                storePopulator.generateProductName(categoryName),
                storePopulator.generateProductRate(),
                storePopulator.generateProductPrice()
        );
    }

    public static int getRandomProductsCount() {
        return new Random().nextInt(RANDOM_MAX - RANDOM_MIN) + RANDOM_MIN;
    }


}
