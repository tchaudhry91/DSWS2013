package commands;

import network.Session;
import network.TcpSession;

import java.io.UnsupportedEncodingException;

public class ConnectCommand extends Command {
    public ConnectCommand(Context context, String[] parameters, String line) {
        super(context, parameters, line);
    }

    @Override
    public boolean execute() {
        Session session = context.getSession();
        if (session != null) {
            // There is an already active session.
            // Need to ask the user to disconnect first.
            writeResponse("A session is already active. Please disconnect first.");
            return true;
        }
        // TODO Create a session instance...
        session = new TcpSession(parameters[0], Integer.parseInt(parameters[1]));
        if (session.connect()) {
            byte[] responseBytes = session.receive();
            try {
                String response = new String(responseBytes, "ASCII");
                writeResponse(response);
            } catch (UnsupportedEncodingException e) {
                return false;
            }
            context.setSession(session);
            return true;
        }
        return false;
    }

    @Override
    public boolean isValid() {
        if (parameters == null || parameters.length != 2) {
            return false;
        }
        // Check that a port is a number
        try {
            Integer.parseInt(parameters[1]);
        } catch (NumberFormatException exc) {
            return false;
        }

        return true;
    }
}
