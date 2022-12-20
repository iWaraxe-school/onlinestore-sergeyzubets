package by.issoft.consoleApp.utilities.consoleCommands;

public class Top extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.TOP.toString())) {
            sorting.printTopProducts();
            return true;
        }
        return handleNext(consoleInput);
    }
}