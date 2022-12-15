package by.issoft.store.utilities;

import by.issoft.domain.Product;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;

public class StoreComparator implements Comparator<Product> {

    private Map<String, String> sortRules;

    public StoreComparator(Map<String, String> sortingRules) {
        this.sortRules = sortingRules;
    }

    @Override
    public int compare(Product product1, Product product2) {
        CompareToBuilder compareToBuilder = new CompareToBuilder();

        for (Map.Entry<String, String> item : sortRules.entrySet()) {
            try {
                if (sortRules.get(item.getKey()).equals(SortOption.ASC.toString())) {
                    compareToBuilder.append(
                                    this.getPropertyValue(product1, item.getKey()),
                                    this.getPropertyValue(product2, item.getKey()))
                            .toComparison();
                } else {
                    compareToBuilder.append(
                                    this.getPropertyValue(product2, item.getKey()),
                                    this.getPropertyValue(product1, item.getKey()))
                            .toComparison();
                }
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return compareToBuilder.toComparison();
    }

    private Object getPropertyValue(Product product, String property) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String productClassMethodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
        Object result = product.getClass().getMethod(productClassMethodName).invoke(product);
        if ("String".equals(result.getClass().toString())) {
            return result.toString().toLowerCase();
        }
        return result;
    }

    public Map<String, String> getSortRules() {
        return sortRules;
    }

    public void setSortRules(Map<String, String> sortRules) {
        this.sortRules = sortRules;
    }


}
