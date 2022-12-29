package by.issoft.consoleApp.utilities;

import by.issoft.consoleApp.utilities.consoleCommands.*;
import by.issoft.store.Store;
import lombok.SneakyThrows;

import java.util.Scanner;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class ConsoleApp {
    private static boolean isConsoleEnabled = true;

    @SneakyThrows
    public void start() {
//        System.out.println(Store.WELCOME_MESSAGE + '\n');     // was moved to HTTP Server
//        new ThreadHelper().cleanUpCart();                     // was moved to HTTP Server
        Scanner scanner = new Scanner(System.in);
        while (isConsoleEnabled) {
            System.out.println(AVAILABLE_COMMANDS);
            String consoleInput = scanner.nextLine().toUpperCase(Store.getInstance().getStoreLocale());
            ConsoleCommandHandler consoleCommandHandler = ConsoleCommandHandler.link(
                    new Cart(),
                    new Catalog(),
                    new Order(),
                    new Quit(),
                    new Recreate(),
                    new Sort(),
                    new Top(),
                    new UnacceptableCommand());
            consoleCommandHandler.isCommandHandled(consoleInput);
        }
        //System.exit(0);                                       // was moved to HTTP Server
    }

    public void setConsoleEnabled(boolean consoleEnabled) {
        isConsoleEnabled = consoleEnabled;
    }
}