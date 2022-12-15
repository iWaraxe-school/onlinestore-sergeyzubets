package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.utilities.StoreComparator;
import com.google.common.base.Preconditions;

import java.util.*;
import java.util.stream.Collectors;

import static by.issoft.store.utilities.StoreConstants.StoreSorting.*;

public class Sorting {

    private final List<String> sortingCong;
    private final StoreComparator comparator;

    public Sorting() {
        ConfigParser configParser = new ConfigParser();
        Map<String, String> sortConf = configParser.getSortingConfigFromFile();
        comparator = new StoreComparator(sortConf);
        sortingCong = new ArrayList<>(Collections.singleton(sortConf.toString()));
    }

    public void printSortedProducts(List<Product> listOfProducts) {
        String sortingParam = sortingCong.toString()
                .replace("[{", "")
                .replace("}]", "")
                .replace("=", " - ");
        List<Product> sortedListOfProducts = listOfProducts.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedListOfProducts.size() > 0, NO_PRODUCTS_TO_SORT);
        String sortResult = SORT_RESULT_DESCRIPTION + sortingParam + '\n'
                + sortedListOfProducts.stream()
                .findFirst()
                .get()
                .toStringAsTable(sortedListOfProducts);
        System.out.println(sortResult);
    }

    public void printTopProducts() {
        List<Product> sortedList = Store.getInstance().getListOfCategories().stream()
                .map(Category::getListOfProducts)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(TOP_X_BY_PRICE_PRODUCTS)
                .collect(Collectors.toList());
        Preconditions.checkArgument(sortedList.size() > 0, NO_PRODUCTS_TO_SORT);
        String stringBuilder = "Top " + TOP_X_BY_PRICE_PRODUCTS + " most expensive products in the store: "
                + '\n' + sortedList.stream()
                .findFirst()
                .get()
                .toStringAsTable(sortedList);
        System.out.println(stringBuilder);
    }
}
