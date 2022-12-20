package by.issoft.consoleApp.utilities;

import by.issoft.consoleApp.utilities.consoleCommands.*;
import by.issoft.store.Store;
import by.issoft.store.helper.ThreadHelper;

import java.util.Scanner;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class ConsoleApp {

    public static final String WELCOME_MESSAGE = '\n' + "Hello! Welcome to the Store. The Store language is "
            + Store.getInstance().getStoreLocale().getDisplayLanguage() + ". The Store currency is "
            + Store.getInstance().getStoreCurrency();
    private static boolean isConsoleEnabled = true;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE + '\n');
        ThreadHelper threadHelper = new ThreadHelper();
        threadHelper.cleanUpCart();
        while (isConsoleEnabled) {
            System.out.println(AVAILABLE_COMMANDS);
            String consoleInput = scanner.nextLine().toUpperCase(Store.getInstance().getStoreLocale());
            ConsoleCommandHandler consoleCommandHandler = ConsoleCommandHandler.link(
                    new Cart(),
                    new Order(),
                    new PrintAllGods(),
                    new Quit(),
                    new Sort(),
                    new Top(),
                    new UnacceptableCommand());
            consoleCommandHandler.isCommandHandled(consoleInput);
        }
        threadHelper.turnOffCleanUpCartThread();
    }

    public boolean isConsoleEnabled() {
        return isConsoleEnabled;
    }

    public void setConsoleEnabled(boolean consoleEnabled) {
        isConsoleEnabled = consoleEnabled;
    }
}