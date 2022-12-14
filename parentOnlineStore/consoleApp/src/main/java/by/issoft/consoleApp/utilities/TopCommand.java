package by.issoft.consoleApp.utilities;

import by.issoft.store.helper.Sorting;

public class TopCommand extends ConsoleCommandHandler {

    private final Sorting sorting = new Sorting();

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.TOP.toString())) {
            sorting.printTopProducts();
            return true;
        }
        return handleNext(consoleInput);
    }
}
