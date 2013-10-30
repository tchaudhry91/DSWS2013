package commands;


import java.io.PrintStream;

/**
 * An abstract base class that all command classes need to implement.
 */
public abstract class Command {
    protected String[] parameters;
    protected Context context;
    protected String line;

    public Command(Context context, String[] parameters, String line) {
        this.parameters = parameters;
        this.context = context;
        this.line = line;
    }

    public abstract boolean execute();

    /**
     * Prints a response to the command
     * @param response The string representing the command's response
     */
    protected void writeResponse(String response) {
        final PrintStream stream = context.getOutputStream();
        stream.println(response);
    }

    /**
     * Checks whether the parameters of the command are valid.
     * @return
     */
    public abstract boolean isValid();
}
