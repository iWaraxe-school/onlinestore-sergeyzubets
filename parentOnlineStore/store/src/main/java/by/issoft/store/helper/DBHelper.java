package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.utilities.CategoryFactory;
import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import by.issoft.store.Store;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.*;

import static by.issoft.store.utilities.StoreConstants.Database.*;


public class DBHelper {

    @SneakyThrows
    public static Connection getConnection() {
        //return DriverManager.getConnection(JDBC_URL + "AUTO_SERVER=TRUE", DB_USER, DB_PASS);
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    @SneakyThrows
    public static Statement getStatement() {
        return getConnection().createStatement();
    }

    public void populateStoreDatabase() {
        CategoryFactory categoryFactory = new CategoryFactory();
        new ArrayList<>(categoryFactory.getCategoryMap().keySet())
                .forEach(category -> addCategoryToDatabase(categoryFactory.getCategoryByType(category)));
        Store.getInstance().setCategoryList(getCategoriesFromDatabase());
        Store.getInstance().getListOfCategories().forEach(category -> populateCategoryWithProducts(category.getCategoryName()));
    }

    @SneakyThrows
    public void addCategoryToDatabase(Category category) {
        Store.getInstance().addCategory(category);
        PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_NEW_CATEGORY);
        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.executeUpdate();
        getConnection().close();
    }

    @SneakyThrows
    public Set<Category> getCategoriesFromDatabase() {
        CategoryFactory categoryFactory = new CategoryFactory();
        ResultSet resultSet = getStatement().executeQuery(GET_ALL_CATEGORIES_FROM_DB);
        Set<Category> listOfCategories = new LinkedHashSet<>();
        while (resultSet.next()) {
            listOfCategories.add(categoryFactory.getCategoryByCategoryName(resultSet.getString("CATEGORY_NAME")));
        }
        getConnection().close();
        return listOfCategories;
    }

    @SneakyThrows
    public void addProductToDatabase(String categoryName) {
        PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_NEW_PRODUCT);
        Product product = new StoreHelper().generateNewProduct(categoryName);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, String.valueOf(product.getRate()));
        preparedStatement.setString(3, String.valueOf(product.getPrice()));
        preparedStatement.setString(4, categoryName);

        new CategoryFactory().getCategoryByCategoryName(categoryName).addProduct(product);

        preparedStatement.executeUpdate();
        getConnection().close();
    }

    @SneakyThrows
    public List<Product> getProductsFromDatabase() {
        ResultSet resultSet = getStatement().executeQuery(GET_ALL_PRODUCTS_FROM_DB);
        List<Product> listOfProducts = new ArrayList<>();
        while (resultSet.next()) {
            listOfProducts.add(new Product(
                            ProductName.of(resultSet.getString("NAME")),
                            Rate.of(resultSet.getInt("RATE")),
                            Price.of(resultSet.getInt("PRICE"))
                    )
            );
        }
        getConnection().close();
        return listOfProducts;
    }

    private void populateCategoryWithProducts(String categoryName) {
        for (int i = 0; i <= StoreHelper.getRandomProductsCount(); i++) {
            addProductToDatabase(categoryName);
        }
    }

}
