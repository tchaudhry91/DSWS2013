package commands;

import network.Session;

import java.io.UnsupportedEncodingException;

public class SendCommand extends Command {
    public SendCommand(Context context, String[] parameters, String line) {
        super(context, parameters, line);
    }

    @Override
    public boolean execute() {
        final Session session = context.getSession();
        if (session == null) {
            writeResponse("No active connection.");
            return false;
        }

        String message = line.replaceAll("^\\s+", "").replaceAll("^send ", "") + "\r";
        try {
            session.send(message.getBytes("ASCII"));
        } catch (UnsupportedEncodingException e) {
            writeResponse("Could not encode the given string using the ASCII encoding.");
            return false;
        }

        try {
            final String response = new String(session.receive(), "ASCII");
            writeResponse(response);
        } catch (UnsupportedEncodingException e) {
            writeResponse("Invalid response - unable to decode using the ASCII encoding");
            return false;
        }

        return true;
    }

    @Override
    public boolean isValid() {
        return parameters != null;
    }
}
