package by.issoft.store.helper;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.utilities.*;
import com.google.common.base.Preconditions;
import lombok.SneakyThrows;

import java.util.*;
import java.util.stream.Collectors;

import static by.issoft.store.utilities.StoreConstants.StoreSorting.*;

public class Sorting {

    private final List<String> sortingCong;
    private final Map<String, String> sortConf;
    private static final Map<String, Comparator<Product>> COMPARATOR_MAP = new LinkedHashMap<String, Comparator<Product>>() {{
        put("name", Comparator.comparing(Product::getName));
        put("rate", Comparator.comparing(Product::getRate));
        put("price", Comparator.comparing(Product::getPrice));}};
    DatabaseHelper databaseHelper = new DatabaseHelper();

    public Sorting() {
        ConfigParser configParser = new ConfigParser();
        sortConf = configParser.getSortingConfigFromFile();
        sortingCong = new ArrayList<>(Collections.singleton(sortConf.toString()));
    }

    private Comparator<Product> chooseComparator(Map.Entry<String, String> entry) {
        Comparator<Product> comparator = COMPARATOR_MAP.getOrDefault(entry.getKey(), null);
        if (entry.getValue().equals(SortOption.DESC.toString()) && Objects.nonNull(comparator)) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    @SneakyThrows
    private Comparator<Product> createComparator(Map<String, String> sortConf) {
        return sortConf.entrySet().stream()
                .map(this::chooseComparator)
                .filter(Objects::nonNull)
                .reduce(Comparator::thenComparing)
                .orElseThrow(Exception::new);
    }

    public String getSortedProducts() {
        String sortingParam = sortingCong.toString()
                .replace("[{", "")
                .replace("}]", "")
                .replace("=", " - ");
        List<Product> sortedListOfProducts = databaseHelper.getAllProductsFromDatabase().stream()
                .sorted(createComparator(sortConf))
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedListOfProducts.size() > 0, NO_PRODUCTS_TO_SORT);
        return SORT_RESULT_DESCRIPTION + sortingParam + '\n'
                + sortedListOfProducts.stream()
                .findFirst()
                .get()
                .toStringAsTable(sortedListOfProducts);
    }

    //old comparator implementation
    public String legacyGetSortedProducts() {
        String sortingParam = sortingCong.toString()
                .replace("[{", "")
                .replace("}]", "")
                .replace("=", " - ");
        List<Product> sortedListOfProducts = databaseHelper.getAllProductsFromDatabase().stream()
                .sorted(new StoreComparator(sortConf))
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedListOfProducts.size() > 0, NO_PRODUCTS_TO_SORT);
        return SORT_RESULT_DESCRIPTION + sortingParam + '\n'
                + sortedListOfProducts.stream()
                .findFirst()
                .get()
                .toStringAsTable(sortedListOfProducts);
    }

    public String getTopProducts() {
        List<Product> sortedList = databaseHelper.getAllProductsFromDatabase().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(TOP_X_BY_PRICE_PRODUCTS)
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedList.size() > 0, NO_PRODUCTS_TO_SORT);
        return "Top " + TOP_X_BY_PRICE_PRODUCTS + " most expensive products in the store (price in "
                + Store.getInstance().getStoreCurrency() + "):"
                + '\n' + sortedList.stream()
                .findFirst()
                .get()
                .toStringAsTable(sortedList);
    }
}
