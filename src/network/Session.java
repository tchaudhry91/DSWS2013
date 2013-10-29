package network;

public interface Session {
	/**
	 * Initiates a session connection.
	 * @return true when the connection is successfully initiated
	 * @return false when the connection is not successfully initiated
	 */
	public boolean connect();
	
	/**
	 * Disconnects the session connection.
	 * @return true when the connection is successfully disconnected
	 * @return false when the connection is not successfully disconnected
	 */
	public boolean disconnect();
	
	/**
	 * Sends data to the OutputStream
	 * @param outData an array of bytes to be sent over an open connection.
	 * @return true when data is successfully sent
	 * @return false when data is not successfully sent
	 */
	public boolean send(byte[] outData);
	
	/**
	 * Receives data from the InputStream
	 * @return an array of bytes when data is successfully read from the data
	 */
	public byte[] receive();
}
