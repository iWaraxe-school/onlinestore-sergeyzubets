package by.issoft.consoleApp.utilities;

public abstract class ConsoleCommandHandler {

    private ConsoleCommandHandler next;

    public static ConsoleCommandHandler link(ConsoleCommandHandler first, ConsoleCommandHandler... chain) {
        ConsoleCommandHandler head = first;
        for (ConsoleCommandHandler nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean isCommandHandled(String consoleInput);

    protected boolean handleNext(String consoleInput) {
        if (next == null) {
            return true;
        }
        return next.isCommandHandled(consoleInput);
    }

}
