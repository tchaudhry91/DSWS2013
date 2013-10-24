package ui;

import network.ConnectionHandler;
import network.EchoProtocol;

public class Application {
	
	public static void main(String[] args){
		//Testing Method, Not Final
		ConnectionHandler con = new ConnectionHandler("131.159.52.1",50000);
		con.connectTCP();
		EchoProtocol ep = new EchoProtocol(con);
		ep.initStreams();
		ep.recieve(); //FOR CONNECTION ESTABLISHED MESSAGE
		ep.send("ASDFASDF");
		ep.recieve();
		ep.closeStreams();
		con.disconnectTCP();
	}

}
