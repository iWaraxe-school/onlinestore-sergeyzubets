package by.issoft.consoleApp.utilities.consoleCommands;

public class Top extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        if (consoleInput.equals(SupportedConsoleCommands.TOP.toString())) {
            System.out.println(sorting.getTopProducts());
            return true;
        }
        return handleNext(consoleInput);
    }
}