package exercicio1.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import exercicio1.Message;

public class ClientTCP extends Client {
	
	public ClientTCP(String host1, int port) {
		super(host1, port);
	}

	private Socket clientSocket;
	private DataOutputStream outToServer;
	private DataInputStream inFromServer;
	private int sentMessageSize;
	private int receiveMessageSize;
	
	public void send(Message message) throws IOException, InterruptedException {
		byte[] msg = serialize(message);
		clientSocket = new Socket(host, port);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new DataInputStream(clientSocket.getInputStream());
		sentMessageSize = msg.length;
		outToServer.writeInt(sentMessageSize);
		outToServer.write(msg, 0, sentMessageSize);
		outToServer.flush();
	}
	
	public byte [] receive() throws IOException, InterruptedException{
		receiveMessageSize = inFromServer.readInt();
		byte msg [] = new byte[receiveMessageSize];
		inFromServer.read(msg,0,receiveMessageSize);
		outToServer.close();
		inFromServer.close();
		return msg;
	}
}
