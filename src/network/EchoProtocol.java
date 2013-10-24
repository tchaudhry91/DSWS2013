package network;

import network.ConnectionHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public class EchoProtocol {
	
	ConnectionHandler con;
	OutputStream os;
	InputStream is;
	
	public EchoProtocol(ConnectionHandler con){
		this.con = con;
	}
	
	public boolean initStreams(){
		try{
			this.os = con.client.getOutputStream();
			this.is = con.client.getInputStream();
			return true;
		}
		catch(IOException IOEx){
			//Handle and Log
			return false;
		}
	}
	
	public boolean closeStreams(){
		try{
			this.os.close();
			this.is.close();
			return true;
		}
		catch(IOException IOEx){
			//Handle and Log
			return false;
		}
	}
	
	public boolean send(String message){
		try{
			byte[] outDataTemp = message.getBytes();
			byte delim = '\n'; 
			byte[] outData = new byte[outDataTemp.length + 1];
			System.arraycopy(outDataTemp, 0, outData, 0, outDataTemp.length);
			outData[outData.length-1] = delim;
			os.write(outData);
			os.flush();
			//Log Here
			return true;
		}
		catch(IOException IOEx){
			//Handle and Log
			return false;
		}
	}
	
	public void recieve(){
		try{
			byte[] inData = new byte[128*1024];
			is.read(inData);
			//Print and Log
			String inString = new String(inData);
			System.out.println(inString);//Replace
		}
		catch(IOException IOEx){
			//Handle and Log
		}
	}
}
