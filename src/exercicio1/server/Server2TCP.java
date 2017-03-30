package exercicio1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2TCP extends Server2{
	public Server2TCP(int portNumber) {
		super(portNumber);
	}

	private ServerSocket welcomeSocket;
	private Socket connectionSocket;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
	
	public void sendToClient(byte [] msg) throws IOException{
		sentMessageSize = msg.length;
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg);
		outToClient.flush();

		connectionSocket.close();
		welcomeSocket.close();
		outToClient.close();
		inFromClient.close();
	}
	
	public byte[] receiveFromClient() throws IOException{
		welcomeSocket = new ServerSocket(portNumber);
		connectionSocket = welcomeSocket.accept();
		
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
		
		receivedMessageSize = inFromClient.readInt();
		byte[] msg = new byte[receivedMessageSize];
		inFromClient.read(msg,0,receivedMessageSize);
		
		return msg;
	}
}
