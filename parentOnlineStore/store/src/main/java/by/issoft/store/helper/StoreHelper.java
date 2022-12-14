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

    private static final int lowerRandomLimit = RANDOM_MIN;
    private static final int upperRandomLimit = RANDOM_MAX;
    public static final String categoryPackagePath = CATEGORY_PACKAGE_PATH;

    public StoreHelper() {
    }

    public void populateStoreViaFaker() {
        RandomStorePopulator storePopulator = new RandomStorePopulator();
        Map<Category, Integer> poolOfCategories = populatePoolOfCategories();
        for (Map.Entry<Category, Integer> entry : poolOfCategories.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(
                        storePopulator.generateProductName(entry.getKey().getCategoryName()),
                        storePopulator.generateProductRate(),
                        storePopulator.generateProductPrice());
                entry.getKey().addProduct(product);
            }
            Store.getInstance().addCategory(entry.getKey());
        }
    }

    private static Map<Category, Integer> populatePoolOfCategories() {
        Map<Category, Integer> mapOfCategories = new HashMap<>();
        Reflections reflections = new Reflections(categoryPackagePath);
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        subTypes.forEach(subType -> {
            try {
                mapOfCategories.put(subType.getConstructor().newInstance(), new Random().nextInt(upperRandomLimit - lowerRandomLimit) + lowerRandomLimit);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        return mapOfCategories;
    }
}
