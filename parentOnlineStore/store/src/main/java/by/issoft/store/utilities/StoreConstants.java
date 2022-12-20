package by.issoft.store.utilities;

import java.util.Locale;

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

    interface ConsoleApp extends Store {
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
        String AVAILABLE_COMMANDS = "Please use the following commands:" + '\n'
                + "* " + CATALOG + " - " + CATALOG_DESCRIPTION + '\n'
                + "* " + ORDER + " - " + ORDER_DESCRIPTION + '\n'
                + "* " + SORT + " - " + SORT_DESCRIPTION + '\n'
                + "* " + TOP + " - " + TOP_DESCRIPTION + '\n'
                + "* " + QUIT + " - " + QUIT_DESCRIPTION;
        String BACK_TO_THE_MAIN_MENU = "Please use 'back' command to return to main menu or press Enter button.";
        int MIN_ORDER_LIFE_TIME = 1;
        int MAX_ORDER_LIFE_TIME = 30;
        int CLEAN_UP_CART_INTERVAL = 2;
    }

}