package by.issoft.store.utilities;

import by.issoft.store.helper.DBHelper;
import lombok.SneakyThrows;

import java.sql.*;

import static by.issoft.store.utilities.StoreConstants.Database.*;

public class DBUtility {

    @SneakyThrows
    public static void initDatabase() {
        Connection connection = DBHelper.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_TABLE_OF_PRODUCTS_IF_EXISTS);
        statement.executeUpdate(DROP_TABLE_OF_CATEGORIES_IF_EXISTS);
        statement.executeUpdate(CREATE_TABLE_OF_CATEGORIES);
        statement.executeUpdate(CREATE_TABLE_OF_PRODUCTS);
        DBHelper dbHelper = new DBHelper();
        dbHelper.populateStoreDatabase();
        connection.close();
    }
}