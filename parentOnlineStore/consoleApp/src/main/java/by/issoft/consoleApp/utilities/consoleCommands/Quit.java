package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.consoleApp.utilities.ConsoleApp;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.*;

public class Quit extends ConsoleCommandHandler {

    ConsoleApp consoleApp = new ConsoleApp();

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.QUIT.toString())) {
            System.out.println(GOODBYE_MESSAGE);
            consoleApp.setConsoleEnabled(false);
            return true;
        }
        return handleNext(consoleInput);
    }
}