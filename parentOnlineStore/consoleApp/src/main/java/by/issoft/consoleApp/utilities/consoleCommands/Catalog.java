package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.store.Store;

public class Catalog extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.CATALOG.toString())) {
            System.out.println(Store.getInstance());
            return true;
        }
        return handleNext(consoleInput);
    }
}
