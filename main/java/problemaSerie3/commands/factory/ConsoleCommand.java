package problemaSerie3.commands.factory;

import java.util.regex.Pattern;

public abstract class ConsoleCommand implements IConsoleCommand {

    protected Pattern regex;
    protected String description;

    /**
     * Creates a command from a pattern and description
     * @param regex Regex to parse
     * @param desc Description of the command
     */
    public ConsoleCommand(Pattern regex, String desc) {
        this.regex = regex;
        this.description = desc;
    }

    /**
     * Match the command with the console input line
     * @param str Line from the console
     * @return True if matches
     */
    public boolean match(String str) {
        return regex.matcher(str).matches();
    }

    public abstract void run(String cmd);

    /**
     * Splits the input line by spaces
     * @param str
     * @return
     */
    public String[] parse(String str) {
        return str.split(" ");
    }

    /**
     * @return Command description
     */
    public String getDescription() {
        return description;
    }
}
