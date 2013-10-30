package commands;

import network.Session;

public class DisconnectCommand extends Command {

    public DisconnectCommand(Context context, String[] parameters, String line) {
        super(context, parameters, line);
    }

    @Override
    public boolean execute() {
        final Session session = context.getSession();
        if (session == null) {
            writeResponse("No active connection.");
            return false;
        }

        context.setSession(null);
        if (session.disconnect()) {
            writeResponse("Successfully disconnected");
            return true;
        } else {
            writeResponse("Problem while closing the connection.");
            return false;
        }
    }

    @Override
    public boolean isValid() {
        return parameters != null && parameters.length == 0;
    }
}
