package by.issoft.consoleApp.utilities.consoleCommands;

public class TopCommand extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommand.TOP.toString())) {
            sorting.printTopProducts();
            return true;
        }
        return handleNext(consoleInput);
    }
}