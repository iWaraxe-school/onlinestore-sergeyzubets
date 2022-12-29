package by.issoft.store.helper;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.utilities.*;
import by.issoft.store.Store;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.*;

import static by.issoft.store.utilities.StoreConstants.Database.*;
import static by.issoft.store.utilities.StoreConstants.Database.Requests.*;

public class DatabaseHelper {

    @SneakyThrows
    public static Connection getConnection() {
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
            listOfCategories.add(categoryFactory.getCategoryByCategoryName(resultSet.getString(CATEGORY_TABLE_CATEGORY_NAME_COLUMN_NAME)));
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
        preparedStatement.setString(4, String.valueOf(getCategoryIdByName(categoryName)));
        new CategoryFactory().getCategoryByCategoryName(categoryName).addProduct(product);
        preparedStatement.executeUpdate();
        getConnection().close();
    }

    @SneakyThrows
    public List<Product> getAllProductsFromDatabase() {
        ResultSet resultSet = getStatement().executeQuery(GET_ALL_PRODUCTS_FROM_DB);
        List<Product> listOfProducts = new ArrayList<>();
        while (resultSet.next()) {
            listOfProducts.add(new Product(
                    ProductName.of(resultSet.getString(PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME)),
                    Rate.of(resultSet.getInt(PRODUCTS_TABLE_PRODUCT_RATE_COLUMN_NAME)),
                    Price.of(resultSet.getInt(PRODUCTS_TABLE_PRODUCT_PRICE_COLUMN_NAME))));
        }
        getConnection().close();
        return listOfProducts;
    }

    private void populateCategoryWithProducts(String categoryName) {
        for (int i = 0; i <= StoreHelper.getRandomProductsCount(); i++) {
            addProductToDatabase(categoryName);
        }
    }

    @SneakyThrows
    public Product getProductByNameFromDatabase(String productName) {
        ResultSet resultSet = getStatement().executeQuery(GET_PRODUCT_BY_NAME_FROM_DB + "'" + productName + "'");
        resultSet.next();
        Product productByNameFromDatabase = new Product(
                ProductName.of(resultSet.getString(PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME)),
                Rate.of(resultSet.getInt(PRODUCTS_TABLE_PRODUCT_RATE_COLUMN_NAME)),
                Price.of(resultSet.getInt(PRODUCTS_TABLE_PRODUCT_PRICE_COLUMN_NAME)));
        getConnection().close();
        return productByNameFromDatabase;
    }

    @SneakyThrows
    public void addPurchasedGodsToDatabase(Product product) {
        PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_PURCHASED_PRODUCT);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, String.valueOf(product.getRate()));
        preparedStatement.setString(3, String.valueOf(product.getPrice()));
        preparedStatement.setString(4, String.valueOf(getCategoryIdByProductName(product.getName())));
        preparedStatement.executeUpdate();
        getConnection().close();
    }

    @SneakyThrows
    public void removeAllPurchasedGodsFromDatabase() {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_PURCHASED_PRODUCTS);
        preparedStatement.executeUpdate();
        getConnection().close();
    }

    @SneakyThrows
    public List<Product> getAllPurchasedGodsFromDatabase() {
        ResultSet resultSet = getStatement().executeQuery(GET_ALL_PURCHASED_PRODUCT);
        List<Product> listOfProducts = new ArrayList<>();
        while (resultSet.next()) {
            listOfProducts.add(new Product(
                    ProductName.of(resultSet.getString(PURCHASED_PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME)),
                    Rate.of(resultSet.getInt(PURCHASED_PRODUCTS_PRODUCT_RATE_COLUMN_NAME)),
                    Price.of(resultSet.getInt(PURCHASED_PRODUCTS_PRODUCT_PRICE_COLUMN_NAME))));
        }
        getConnection().close();
        return listOfProducts;
    }

    @SneakyThrows
    public List<Product> getProductsPerCategoryFromDatabase(Category category) {
        ResultSet resultSet = getStatement().executeQuery(GET_ALL_PRODUCTS_PER_CATEGORY_FROM_DB + getCategoryIdByName(category.getCategoryName()));
        List<Product> listOfProducts = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product(
                    ProductName.of(resultSet.getString(PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME)),
                    Rate.of(resultSet.getInt(PRODUCTS_TABLE_PRODUCT_RATE_COLUMN_NAME)),
                    Price.of(resultSet.getInt(PRODUCTS_TABLE_PRODUCT_PRICE_COLUMN_NAME)));
            listOfProducts.add(product);
            new CategoryFactory().getCategoryByCategoryName(category.getCategoryName()).addProduct(product);
        }
        getConnection().close();
        return listOfProducts;
    }

    @SneakyThrows
    private int getCategoryIdByName(String categoryName) {
        ResultSet resultSet = getStatement().executeQuery(GET_CATEGORY_ID_BY_NAME + "'" + categoryName + "'");
        resultSet.next();
        return resultSet.getInt(CATEGORY_TABLE_PRIMARY_KEY);
    }

    @SneakyThrows
    private int getCategoryIdByProductName(String productName) {
        ResultSet resultSet = getStatement().executeQuery(GET_PRODUCT_ID_BY_NAME + "'" + productName + "'");
        resultSet.next();
        return resultSet.getInt(PRODUCTS_TABLE_FOREIGN_KEY_KEY);
    }
}
