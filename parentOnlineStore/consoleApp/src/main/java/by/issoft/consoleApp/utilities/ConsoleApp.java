package by.issoft.consoleApp.utilities;

import by.issoft.store.Store;
import by.issoft.store.helper.Sorting;

import java.util.Scanner;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;


public class ConsoleApp {

    private static final String greetingMessage = GREETING_MESSAGE;
    private static final String goodbyeMessage = GOODBYE_MESSAGE;
    private static final String availableCommands = AVAILABLE_COMMANDS;
    private static final String sort = SORT;
    private static final String top = TOP;
    private static final String quit = QUIT;
    private static final String unknownCommand = INCORRECT_INPUT;
    private static boolean isConsoleEnabled = true;
    private final Scanner scanner;
    private final Sorting sorting;

    public ConsoleApp() {
        scanner = new Scanner(System.in);
        sorting = new Sorting();
    }

    public void start() {
        System.out.println(greetingMessage);
        while (isConsoleEnabled) {
            System.out.println(availableCommands);
            String consoleInput = scanner.nextLine().toLowerCase(Store.getInstance().getStoreLocale());
            switch (consoleInput) {
                case sort: {
                    sorting.printSortedProducts(Store.getInstance().getListOfAllProducts());
                    break;
                }
                case top: {
                    sorting.printTopProducts();
                    break;
                }
                case quit: {
                    System.out.println(goodbyeMessage);
                    setIsConsoleEnabled(false);
                    break;
                }
                default:
                    System.out.println(unknownCommand);
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static boolean isIsConsoleEnabled() {
        return isConsoleEnabled;
    }

    public static void setIsConsoleEnabled(boolean isConsoleEnabled) {
        ConsoleApp.isConsoleEnabled = isConsoleEnabled;
    }
}
