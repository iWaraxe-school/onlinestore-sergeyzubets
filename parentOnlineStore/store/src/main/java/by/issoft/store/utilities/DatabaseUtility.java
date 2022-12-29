package by.issoft.store.utilities;

import by.issoft.store.helper.DatabaseHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

import static by.issoft.store.utilities.StoreConstants.Database.Requests.*;

@Slf4j
public class DatabaseUtility {

    @SneakyThrows
    public static void dropAndRefillDatabase() {
        Connection connection = DatabaseHelper.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_TABLE_OF_PRODUCTS_IF_EXISTS);
        statement.executeUpdate(DROP_TABLE_OF_PURCHASED_PRODUCTS_IF_EXISTS);
        statement.executeUpdate(DROP_TABLE_OF_CATEGORIES_IF_EXISTS);
        fillDatabase();
        log.info(RELOAD_DB);
    }

    @SneakyThrows
    public static void fillDatabase() {
        Connection connection = DatabaseHelper.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_TABLE_OF_CATEGORIES);
        statement.executeUpdate(CREATE_TABLE_OF_PRODUCTS);
        statement.executeUpdate(CREATE_TABLE_OF_PURCHASED_PRODUCTS);
        new DatabaseHelper().populateStoreDatabase();
        connection.close();
    }

    @SneakyThrows
    public static void readFromDatabase() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.getCategoriesFromDatabase();
        databaseHelper.getAllProductsFromDatabase();
        DatabaseHelper.getConnection().close();
        log.info(LOAD_DB);
    }
}