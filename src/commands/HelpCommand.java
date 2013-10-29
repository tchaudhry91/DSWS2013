package commands;

public class HelpCommand extends Command {

	public HelpCommand(Context context, String[] parameters) {
		super(context, parameters);
	}

	@Override
	public boolean execute() {
		if (parameters.length == 0) {
			writeResponse("You can choose one of these commands: \n help connect \n help disconnect \n help send \n help quit \n");
			return true;
		} 
		else if ("connect".equals(parameters[0])) {
			writeResponse("With the connect command you can try to establish network with the server. \n"
					+ "After calling 'connect' command you should type conncetion address and port of the server. \n"
					+ "Exapmle: connect <address> <port> \n");
			return true;
		} 
		else if ("disconnect".equals(parameters[0])) {
			writeResponse("After calling the 'disconnect' command you will be disconnected from the server. \n"
					+ "Example: disconnect \n");
			return true;
		}
		else if ("send".equals(parameters[0])) {
			writeResponse("After calling 'send' command you shold enter the text of the massage that will be send. \n"
					+ "Example: send <message> \n");
			return true;
		}
		else if ("quit".equals(parameters[0])) {
			writeResponse("After calling 'quit' command you program will be closed. \n"
					+ "Example: quit \n");
			return true;
		}
		return false;
	}

	@Override
	public boolean isValid() {
		if (parameters.length == 0) {
			return true;
		} else if ("connect".equals(parameters[0])
				|| "disconnect".equals(parameters[0])
				|| "send".equals(parameters[0]) || "quit".equals(parameters[0])) {
			return true;
		}
		return false;
	}
}
