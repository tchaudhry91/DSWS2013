package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import commands.QuitCommand;

public class TcpSession implements Session {
	
	Logger log;
	
	Socket client;
	String host;
	int port; 
	boolean streams;
	InputStream is;
	OutputStream os;

    private final static int MAX_RESPONSE_SIZE = 128 * 1024; // 128 KB
	
	/**
	 * Create an Instance of TcpSession 
	 * @param host which specifies the server address
	 * @param port	which specifies the port server is listening on
	 */
	public TcpSession(String host, int port){
		this.host = host;
		this.port = port;
		
		log = Logger.getLogger(TcpSession.class);
		log.info("Compiling source file: " + log.getName());
		log.info("You have made the TcpSession.");
	}
	
	/**
	 * Initiates a TcpSession connection and initializes streams
	 * @return true when the connection is successfully initiated
	 * @return false when the connection is not successfully initiated
	 */
	public boolean connect() {
		try{
			client = new Socket(host,port);
			streams = initStreams();
			
			log.info("You are connected to the server: " + host + " / " + port);
			return true;
		}
		catch(UnknownHostException UHEx){
			log.error("Exception while trying to connect with the server. Type of excepton: UnknownHost");
			log.error(UHEx.getStackTrace());
			return false;
		}
		catch(IOException IOEx){
			log.error("Exception while trying to connect with the server. Type of excepton: IO.");
			log.error(IOEx.getStackTrace());
			return false;
		}
	}

	/**
	 * Disconnects the TcpSession connection and closes streams
	 * @return true when connection is successfully terminated
	 * @return false when connection is not successfully terminated
	 */
	public boolean disconnect() {
		try{
			is.close();
			os.close();
			client.close();
			
			log.info("You are disconnected from the server.");
			return true;
		}
		catch(IOException IOEx){
			log.error("Exception while trying to disconnect with the server. Type of excepton: IO.");
			log.error(IOEx.getStackTrace());
			return false;
		}
	}
	
	/**
	 * Initialize the input/output streams for the current connection
	 * @return true if the streams are successfully initialized
	 * @return false if the streams are not successfully initialized
	 */
	public boolean initStreams(){
		try{
			is = client.getInputStream();
			os = client.getOutputStream();
			
			log.info("Your input and output stream are regularly initialized.");
			return true;
		}
		catch(IOException IOEx){
			log.error("Exception trying to initialize input and output streams. Type of exception: IO.");
			log.error(IOEx.getStackTrace());
			return false;
		}
	}

	/**
	 * Sends an array of bytes to the output stream
	 * @param outData an array of bytes to be sent over an open connection
	 * @return true when the data is successfully sent
	 * @return false when the data is not successfuly sent
	 */
	public boolean send(byte[] outData) {
		try{
			os.write(outData);
			os.flush();
			
			log.info("Your data is successfully sent.");
			return true;
		}
		catch(IOException IOEx){
			log.error("Exception trying to send data. Type of exception: IO.");
			log.error(IOEx.getStackTrace());
			return false;
		}
	}
	
	/**
	 * Receives data from the input stream
	 * @return an array of bytes containing data read from the stream
	 * @return null if no data is read
	 */
	public byte[] receive() {
		try{
			int alreadyRead = 0;
			final int bufferSize = 256;
			byte[] inData = new byte[MAX_RESPONSE_SIZE];
			byte[] inDataBuff = new byte[bufferSize];
            while (true) {
				int bytesRead = is.read(inDataBuff, 0, bufferSize);
                System.arraycopy(inDataBuff, 0, inData, alreadyRead, bytesRead);
				alreadyRead += bytesRead;
                if (alreadyRead >= MAX_RESPONSE_SIZE) {
                    is.skip(is.available());
                    break;
                }
                if (is.available() == 0) {
                    break;
                }
			}
			if(alreadyRead == 0){
				return null;
			}
			else{
				log.info("Your data is successfully read.");
                // Return only the data that was received from the server
				return Arrays.copyOf(inData, alreadyRead);
			}
		}
		catch(IOException IOEx){
			log.error("Exception trying to read data. Type of exception: IO.");
			log.error(IOEx.getStackTrace());
			return null;
		}
	}

}
