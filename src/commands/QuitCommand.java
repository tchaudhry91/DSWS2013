package commands;

import org.apache.log4j.Logger;

import network.Session;

public class QuitCommand extends Command {
	Logger log;
	
    public QuitCommand(Context context, String[] parameters, String line) {
        super(context, parameters, line);
        
        log = Logger.getLogger(QuitCommand.class);
		log.info("Compiling source file: " + log.getName());
		log.info("You have made the quit command.");
    }

    @Override
    public boolean execute() {
        // Make sure to close an open connection, if any.
        final Session session = context.getSession();
        if (session != null) {
            session.disconnect();
        }
        
        log.info("Application exit.");
        writeResponse("Application exit!");
        System.exit(0);
        return true;
    }

    @Override
    public boolean isValid() {
        if(parameters != null && parameters.length == 0) {
        	log.info("Your help command is valid.");
        	return true;
        }
        return false;
    }
}
