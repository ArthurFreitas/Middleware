package exercicio1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import exercicio1.common.Message;

public class Server1TCP extends Server1 {
	
	public Server1TCP(int portNumber, String host2, int portOfServer2) {
		super(portNumber, host2, portOfServer2);
		// TODO Auto-generated constructor stub
	}

	private ServerSocket welcomeSocket;
	private Socket connectionSocket;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private DataInputStream inFromServer;
	private int receiveMessageSize;
	
	
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

	@Override
	public void sendToServer(Message message) throws IOException, InterruptedException {
		byte[] msg = serializer.serialize(message);
		clientSocket = new Socket(host, portOfServer2);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new DataInputStream(clientSocket.getInputStream());
		sentMessageSize = msg.length;
		outToServer.writeInt(sentMessageSize);
		outToServer.write(msg, 0, sentMessageSize);
		outToServer.flush();
		
	}

	@Override
	public byte[] receiveFromServer() throws IOException, InterruptedException {
		receiveMessageSize = inFromServer.readInt();
		byte msg [] = new byte[receiveMessageSize];
		inFromServer.read(msg,0,receiveMessageSize);
		outToServer.close();
		inFromServer.close();
		return msg;
	}
}
