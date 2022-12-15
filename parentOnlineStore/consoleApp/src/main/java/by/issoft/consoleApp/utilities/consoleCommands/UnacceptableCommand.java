package by.issoft.consoleApp.utilities.consoleCommands;

import static by.issoft.store.utilities.StoreConstants.ConsoleApp.INCORRECT_INPUT;

public class UnacceptableCommand extends ConsoleCommandHandler {

    @Override
    public boolean isCommandHandled(String consoleInput) {
        try {
            SupportedConsoleCommand.valueOf(consoleInput);
        } catch (IllegalArgumentException e) {
            System.out.println(INCORRECT_INPUT);
            return true;
        }
        return handleNext(consoleInput);
    }
}