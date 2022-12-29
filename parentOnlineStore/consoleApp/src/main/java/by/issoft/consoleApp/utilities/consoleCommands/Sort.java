package by.issoft.consoleApp.utilities.consoleCommands;

public class Sort extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.SORT.toString())) {
            System.out.println(sorting.getSortedProducts());
            return true;
        }
        return handleNext(consoleInput);
    }
}