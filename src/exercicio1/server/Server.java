package exercicio1.server;

import java.io.IOException;
import exercicio1.common.Message;
import exercicio1.common.Serializer;

public abstract class Server implements Runnable {
	
	protected int portNumber;
	protected Message receivedMessage;
	protected Message responseMessage;
	protected Serializer serializer = new Serializer();
	
	public Server (int portNumber){
		this.portNumber = portNumber;
	}
	
	public void run(){
		System.out.println("Server Listening at Port Number:" + portNumber);
		while(true){
			try {
				receivedMessage = serializer.deserialize(receiveFromClient());
				responseMessage = doStuff(receivedMessage);
				sendToClient(serializer.serialize(responseMessage));
			} catch (ClassNotFoundException | IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void sendToClient(byte [] msg) throws IOException;
	public abstract byte[] receiveFromClient() throws IOException;
	public abstract Message doStuff(Message message) throws ClassNotFoundException, IOException, InterruptedException;
}
