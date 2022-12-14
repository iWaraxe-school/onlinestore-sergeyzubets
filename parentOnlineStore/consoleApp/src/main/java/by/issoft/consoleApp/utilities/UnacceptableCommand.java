package by.issoft.consoleApp.utilities;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.INCORRECT_INPUT;

public class UnacceptableCommand extends ConsoleCommandHandler {

    private static final String unknownCommand = INCORRECT_INPUT;

    @Override
    public boolean isCommandHandled(String consoleInput) {
        try {
            SupportedConsoleCommands.valueOf(consoleInput);
        } catch (IllegalArgumentException e) {
            System.out.println(unknownCommand);
            return true;
        }
        return handleNext(consoleInput);
    }

}
