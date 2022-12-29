package by.issoft.store.utilities;

import java.util.Locale;

import static by.issoft.domain.utilities.DomainConstants.CategoryConstants.MAX_CATEGORY_NAME_LENGTH;
import static by.issoft.domain.utilities.DomainConstants.ProductConstants.NameConstants.MAX_PRODUCT_NAME_LENGTH;

public interface StoreConstants {

    interface StoreConfigFile {
        String DEFAULT_CONFIG_FILE_NAME = "config.xml";
        String DEFAULT_CONFIG_FILE_PATH = "/parentOnlineStore/store/src/main/resources/";
        String CONFIG_FILE_IS_NOT_FOUND_ERROR_MESSAGE = "Config File is not found in " + DEFAULT_CONFIG_FILE_PATH;
        String CONFIG_FILE_WITHOUT_CONFIG = "Config File does not contain sorting configuration.";
        String INCORRECT_SORT_OPTION_ERROR_MESSAGE = "Sorting keyword should be "
                + SortOption.ASC + " or " + SortOption.DESC
                + ". The value gotten from the " + DEFAULT_CONFIG_FILE_NAME + " file value is ";
    }

    interface StorePopulator {
        int RANDOM_MIN = 1;
        int RANDOM_MAX = 10;
        String CATEGORY_PACKAGE_PATH = "by.issoft.domain.categories";
    }

    interface Store {
        Locale DEFAULT_STORE_LOCALE = Locale.US;
        String CART_WAS_CLEANED_UP = "The cart with Purchased Gods was cleaned up.";
    }

    interface StoreSorting {
        int TOP_X_BY_PRICE_PRODUCTS = 5;
        String NO_PRODUCTS_TO_SORT = "There are no products to sort";
        String SORT_RESULT_DESCRIPTION = "All store products are sorted with the following configuration: ";
    }

    interface Threads {
        int ORDER_THREADS_POOL_SIZE = 5;
        int MIN_ORDER_LIFE_TIME = 1;
        int MAX_ORDER_LIFE_TIME = 30;
        int CLEAN_UP_CART_INTERVAL = 2;
        String START_PROCESSING_ORDER = "Start processing order ";
        String FINISH_PROCESSING_ORDER = "Finish processing order. Purchased ";
    }

    interface ConsoleApp extends Store {
        String CART = "cart";
        String CART_DESCRIPTION = "To print all Purchased Gods.";
        String CATALOG = "catalog";
        String CATALOG_DESCRIPTION = "To print all products from the store.";
        String TOP = "top";
        String TOP_DESCRIPTION = "To print top " + StoreSorting.TOP_X_BY_PRICE_PRODUCTS + " products of whole store sorted via price by DESC.";
        String SORT = "sort";
        String SORT_DESCRIPTION = "To print products from store according to the sorting configuration.";
        String QUIT = "quit";
        String QUIT_DESCRIPTION = "To exit app";
        String INCORRECT_INPUT = "Unknown command.";
        String GOODBYE_MESSAGE = '\n' + "Thank you for using the Store. See you next time.";
        String ORDER = "order";
        String ORDER_DESCRIPTION = "To create a new order.";
        String RECREATE = "recreate";
        String RECREATE_DESCRIPTION = "To drop current Database, create a new one, and refill it.";
        String AVAILABLE_COMMANDS = "Please use the following commands:" + '\n'
                + "* " + CART + " - " + CART_DESCRIPTION + '\n'
                + "* " + CATALOG + " - " + CATALOG_DESCRIPTION + '\n'
                + "* " + ORDER + " - " + ORDER_DESCRIPTION + '\n'
                + "* " + RECREATE + " - " + RECREATE_DESCRIPTION + '\n'
                + "* " + SORT + " - " + SORT_DESCRIPTION + '\n'
                + "* " + TOP + " - " + TOP_DESCRIPTION + '\n'
                + "* " + QUIT + " - " + QUIT_DESCRIPTION;
    }

    interface Database {
        String JDBC_URL = "jdbc:h2:~/szDB";
        String DB_USER = "ideaUser";
        String DB_PASS = "qwerty";

        interface DatabaseCategoriesTable {
            String CATEGORY_TABLE_NAME = "CATEGORIES";
            String CATEGORY_TABLE_PRIMARY_KEY = "CAT_ID";
            String CATEGORY_TABLE_CATEGORY_NAME_COLUMN_NAME = "CATEGORY_NAME";
        }

        interface DatabaseProductsTable {
            String PRODUCTS_TABLE_NAME = "PRODUCTS";
            String PRODUCTS_TABLE_PRIMARY_KEY = "PROD_ID";
            String PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME = "NAME";
            String PRODUCTS_TABLE_PRODUCT_RATE_COLUMN_NAME = "RATE";
            String PRODUCTS_TABLE_PRODUCT_PRICE_COLUMN_NAME = "PRICE";
            String PRODUCTS_TABLE_FOREIGN_KEY_KEY = "CAT_ID";
        }

        interface DatabasePurchasedProductsTable {
            String PURCHASED_PRODUCTS_TABLE_NAME = "PURCHASED_PRODUCTS";
            String PURCHASED_PRODUCTS_TABLE_PRIMARY_KEY = "PROD_ID";
            String PURCHASED_PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME = "NAME";
            String PURCHASED_PRODUCTS_PRODUCT_RATE_COLUMN_NAME = "RATE";
            String PURCHASED_PRODUCTS_PRODUCT_PRICE_COLUMN_NAME = "PRICE";
            String PURCHASED_PRODUCTS_TABLE_FOREIGN_KEY_KEY = "CAT_ID";
        }

        interface Requests extends DatabaseCategoriesTable, DatabaseProductsTable, DatabasePurchasedProductsTable {
            String DROP_TABLE_OF_PRODUCTS_IF_EXISTS = "DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME;
            String DROP_TABLE_OF_PURCHASED_PRODUCTS_IF_EXISTS = "DROP TABLE IF EXISTS " + PURCHASED_PRODUCTS_TABLE_NAME;
            String DROP_TABLE_OF_CATEGORIES_IF_EXISTS = "DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME;
            String CREATE_TABLE_OF_CATEGORIES = "CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE_NAME + " ("
                    + CATEGORY_TABLE_PRIMARY_KEY + " INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                    + CATEGORY_TABLE_CATEGORY_NAME_COLUMN_NAME + " VARCHAR(" + MAX_CATEGORY_NAME_LENGTH + ") UNIQUE NOT NULL)";
            String CREATE_TABLE_OF_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + PRODUCTS_TABLE_NAME + " ("
                    + PRODUCTS_TABLE_PRIMARY_KEY + " INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                    + PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME + " VARCHAR (" + MAX_PRODUCT_NAME_LENGTH + "), "
                    + PRODUCTS_TABLE_PRODUCT_RATE_COLUMN_NAME + " INTEGER, "
                    + PRODUCTS_TABLE_PRODUCT_PRICE_COLUMN_NAME + " INTEGER, "
                    + PRODUCTS_TABLE_FOREIGN_KEY_KEY + " INT NOT NULL, "
                    + "FOREIGN KEY (" + PRODUCTS_TABLE_FOREIGN_KEY_KEY + ") "
                    + "REFERENCES " + CATEGORY_TABLE_NAME + " (" + CATEGORY_TABLE_PRIMARY_KEY + "))";
            String CREATE_TABLE_OF_PURCHASED_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + PURCHASED_PRODUCTS_TABLE_NAME + " ("
                    + PURCHASED_PRODUCTS_TABLE_PRIMARY_KEY + " INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                    + PURCHASED_PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME + " VARCHAR(" + MAX_PRODUCT_NAME_LENGTH + "), "
                    + PURCHASED_PRODUCTS_PRODUCT_RATE_COLUMN_NAME + " INTEGER, "
                    + PURCHASED_PRODUCTS_PRODUCT_PRICE_COLUMN_NAME + " INTEGER, "
                    + PURCHASED_PRODUCTS_TABLE_FOREIGN_KEY_KEY + " INT NOT NULL, "
                    + "FOREIGN KEY (" + CATEGORY_TABLE_PRIMARY_KEY + ") "
                    + "REFERENCES CATEGORIES (" + CATEGORY_TABLE_PRIMARY_KEY + "))";
            String GET_ALL_CATEGORIES_FROM_DB = "SELECT * FROM " + CATEGORY_TABLE_NAME;
            String GET_CATEGORY_ID_BY_NAME = "SELECT " + CATEGORY_TABLE_PRIMARY_KEY
                    + " FROM " + CATEGORY_TABLE_NAME
                    + " WHERE " + CATEGORY_TABLE_CATEGORY_NAME_COLUMN_NAME + " = ";
            String GET_PRODUCT_ID_BY_NAME = "SELECT " + PRODUCTS_TABLE_FOREIGN_KEY_KEY
                    + " FROM " + PRODUCTS_TABLE_NAME
                    + " WHERE " + PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME + " = ";
            String INSERT_NEW_CATEGORY = "INSERT INTO " + CATEGORY_TABLE_NAME + " (" + CATEGORY_TABLE_CATEGORY_NAME_COLUMN_NAME + ") VALUES(?)";
            String GET_ALL_PRODUCTS_FROM_DB = "SELECT * FROM " + PRODUCTS_TABLE_NAME;
            String GET_ALL_PRODUCTS_PER_CATEGORY_FROM_DB = "SELECT * FROM " + PRODUCTS_TABLE_NAME
                    + " WHERE " + PRODUCTS_TABLE_FOREIGN_KEY_KEY + " = ";
            String GET_PRODUCT_BY_NAME_FROM_DB = "SELECT * FROM " + PRODUCTS_TABLE_NAME
                    + " WHERE " + PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME + " = ";
            String INSERT_NEW_PRODUCT = "INSERT INTO " + PRODUCTS_TABLE_NAME
                    + " (" + PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME + ","
                    + PRODUCTS_TABLE_PRODUCT_RATE_COLUMN_NAME + ","
                    + PRODUCTS_TABLE_PRODUCT_PRICE_COLUMN_NAME + ","
                    + PRODUCTS_TABLE_FOREIGN_KEY_KEY + ") VALUES(?, ?, ?, ?)";
            String GET_ALL_PURCHASED_PRODUCT = "SELECT * FROM " + PURCHASED_PRODUCTS_TABLE_NAME;

            String INSERT_PURCHASED_PRODUCT = "INSERT INTO " + PURCHASED_PRODUCTS_TABLE_NAME + " ("
                    + PURCHASED_PRODUCTS_TABLE_PRODUCT_NAME_COLUMN_NAME + ","
                    + PURCHASED_PRODUCTS_PRODUCT_RATE_COLUMN_NAME + ","
                    + PURCHASED_PRODUCTS_PRODUCT_PRICE_COLUMN_NAME + ","
                    + PURCHASED_PRODUCTS_TABLE_FOREIGN_KEY_KEY + ") VALUES(?, ?, ?, ?)";
            String DELETE_PURCHASED_PRODUCTS = "DELETE FROM " + PURCHASED_PRODUCTS_TABLE_NAME;
            String RELOAD_DB = "The Database was reinitialized successfully.";
            String LOAD_DB = "The Database was loaded successfully.";
        }
    }

    interface Http {
        String HOST = "http://localhost";
        int PORT = 8080;
        String MAIN_PAGE_URL = "/";
        String CATEGORIES_PAGE_URL = "/categories";
        String PRODUCTS_PAGE_URL = "/products";
        String CATALOG_PAGE_URL = "/catalog";
        String TOP_GODS_PAGE_URL = "/top";
        String SORTED_GODS_PAGE_URL = "/sorted";
        String CART_PAGE_URL = "/cart";
        String NEW_ORDER_PAGE_URL = "/new_order";
        String START_SERVER_MESSAGE = "The server is running on port " + PORT;
        String STOP_SERVER_MESSAGE = "Server is stopped";
        int HTTP_SUCCESS_STATUS_RESPONSE_CODE = 200;
        String USERNAME = "admin";
        String PASSWORD = "admin";
        String EMPTY_STORE = "The store is empty. No products to purchase";
    }
}