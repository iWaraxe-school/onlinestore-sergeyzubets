package by.issoft.consoleApp.utilities.consoleCommands;

import by.issoft.store.Store;

public class Sort extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.SORT.toString())) {
            sorting.printSortedProducts(Store.getInstance().getListOfAllProducts());
            return true;
        }
        return handleNext(consoleInput);
    }
}