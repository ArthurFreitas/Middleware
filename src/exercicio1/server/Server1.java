package exercicio1.server;

import java.io.IOException;

import exercicio1.common.Message;

public abstract class Server1 extends Server{
	
	protected String host;
	protected int portOfServer2;
	protected Message receivedMessage;
	private String reversedString;
	
	public Server1(int portNumber, String host2, int portOfServer2){
		super(portNumber);
		this.host = host2;
		this.portOfServer2 = portOfServer2;
	}
	
	public Message doStuff(Message message) throws ClassNotFoundException, IOException, InterruptedException{
		reversedString = reverseString(message.getMessage());
		
		sendToServer(new Message(reversedString));
		receivedMessage = serializer.deserialize(receiveFromServer());
		return receivedMessage;
	}
	
	public String reverseString(String stringToBeReversed){
		return new StringBuilder(stringToBeReversed).reverse().toString();
	}
	
    public abstract void sendToServer(Message message) throws IOException, InterruptedException;
    public abstract byte [] receiveFromServer() throws IOException, InterruptedException;
}
