package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.utilities.StoreComparator;
import by.issoft.store.utilities.StoreConstants;
import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.Collectors;

public class Sorting {

    public static final int topXByPriceProducts = StoreConstants.StoreSorting.TOP_X_BY_PRICE_PRODUCTS;
    public static final String noProductsToSort = StoreConstants.StoreSorting.NO_PRODUCTS_TO_SORT;
    public static Set<String> sortingKeyAttributes;
    public static Collection<String> sortingValueAttributes;
    StoreComparator comparator;
    Store store;

    public Sorting(Store store) {
        ConfigParser configParser = new ConfigParser();
        Map<String, String> sortConf = configParser.getSortingConfigFromFile();
        comparator = new StoreComparator(sortConf);
        sortingKeyAttributes = sortConf.keySet();
        sortingValueAttributes = sortConf.values();
        this.store = store;
    }

    public void printSortedProducts(List<Product> listOfProducts) {
        List<String> listOfSortFields = sortingKeyAttributes.stream()
                .map(String::toUpperCase).collect(Collectors.toList());
        List<String> listOfSortOptions = new ArrayList<>(sortingValueAttributes);

        List<Product> sortedListOfProducts = listOfProducts.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedListOfProducts.size() > 0, noProductsToSort);
        String sortResult = "All store products are sorted by "
                + listOfSortFields.get(0) + " " + listOfSortOptions.get(0) + " primary, by "
                + listOfSortFields.get(1) + " " + listOfSortOptions.get(1) + " secondary, and by "
                + listOfSortFields.get(2) + " " + listOfSortOptions.get(2) + " tertiary" + '\n'
                + sortedListOfProducts.stream()
                .findFirst()
                .get()
                .toStringAsPerLocale(sortedListOfProducts, store.getStoreLocale());
        System.out.println(sortResult);
    }

    public void printTopProducts() {
        List<Product> sortedList = store.getListOfCategories().stream()
                .map(Category::getListOfProducts)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(topXByPriceProducts)
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedList.size() > 0, noProductsToSort);
        String stringBuilder = "Top " + topXByPriceProducts + " most expensive products in the store: " + '\n' + sortedList.stream()
                .findFirst()
                .get()
                .toStringAsPerLocale(sortedList, store.getStoreLocale());
        System.out.println(stringBuilder);
    }
}
