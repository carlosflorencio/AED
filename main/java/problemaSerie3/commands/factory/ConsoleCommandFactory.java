package problemaSerie3.commands.factory;


import problemaSerie3.commands.ExitConsoleCommand;
import problemaSerie3.commands.HelpConsoleCommand;

public class ConsoleCommandFactory {

    private static ConsoleCommand[] commands = { //add commands here
            new HelpConsoleCommand(),
            new ExitConsoleCommand()
    };

    /**
     * Finds the suitable command for that request and execute it
     * @param cmd Input line from the console
     * @return True if command is found and executed
     */
    public static boolean parse(String cmd) {
        for (ConsoleCommand command : commands) {
            if (command.match(cmd)) {
                command.run(cmd);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the commands list
     * @return
     */
    public static ConsoleCommand[] getCommands() {
        return commands;
    }

}
