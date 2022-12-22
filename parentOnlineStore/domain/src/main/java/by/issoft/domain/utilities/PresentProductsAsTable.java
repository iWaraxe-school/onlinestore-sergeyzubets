package by.issoft.domain.utilities;

import by.issoft.domain.Product;

import java.util.*;

public class PresentProductsAsTable {

    public PresentProductsAsTable() {
    }

    public String getProductsAsTable(List<Product> listOfProducts) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList("Product Name", "Rate", "Price"));
        formulateList(rows, listOfProducts);

        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }
        StringBuilder formatBuilder = new StringBuilder();

        for (int maxLength : maxLengths) {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        StringBuilder resultTableOfProducts = new StringBuilder();
        String format = formatBuilder.toString();
        rows.forEach(row -> resultTableOfProducts
                .append(String.format(format, row.toArray(new String[0])))
                .append("\n"));
        return resultTableOfProducts.toString();
    }

    private List<List<String>> formulateList(List<List<String>> rows, List<Product> listOfProducts) {
        listOfProducts.forEach(product -> rows.add(Arrays.asList(
                product.getName(),
                Integer.toString(product.getRate()),
                Integer.toString(product.getPrice()))));
        return rows;
    }

}
