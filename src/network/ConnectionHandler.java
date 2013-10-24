package network;

import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;

public class ConnectionHandler {

	String host;
	int port;
	Socket client;
	
	public ConnectionHandler(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public boolean connectTCP(){
		try {
			//Log Connection Initiation
			client = new Socket(this.host,this.port);
			return true;
		}
		catch(UnknownHostException UHEx){
			//handle and log
			return false;
		}
		catch(IOException IOEx){
			//Handle and Log
			return false;
		}
	}
	
	public boolean disconnectTCP(){
		try {
			client.close();
			//Log
			return true;
		}
		catch(IOException IOEx){
			//Handle and Log
			return false;
		}
	}
	
}
