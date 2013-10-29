package commands;

import network.Session;

public class QuitCommand extends Command {

    public QuitCommand(Context context, String[] parameters) {
        super(context, parameters);
    }

    @Override
    public boolean execute() {
        // Make sure to close an open connection, if any.
        final Session session = context.getSession();
        if (session != null) {
            session.disconnect();
        }

        writeResponse("Application exit!");
        System.exit(0);
        return true;
    }

    @Override
    public boolean isValid() {
        return parameters != null && parameters.length == 0;
    }
}
