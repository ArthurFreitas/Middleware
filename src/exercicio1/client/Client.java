package exercicio1.client;

import java.io.IOException;
import java.util.Random;

import exercicio1.common.Message;
import exercicio1.common.Serializer;

public abstract class Client implements Runnable{
	protected final String TheOneRing = 
			"Three Rings for the Elven-kings under the sky,"
			+ "Seven for the Dwarf-lords in halls of stone,"
			+ "Nine for Mortal Men doomed to die,"
			+ "One for the Dark Lord on his dark throne,"
			+ "In the Land of Mordor where the Shadows lie,"
			+ "One Ring to rule them all,"
			+ "One Ring to find them,"
			+ "One Ring to bring them all and in the darkness bind them,"
			+ "In the Land of Mordor where the Shadows lie";
	
	protected String[] strings = TheOneRing.split(",");
	protected String host;
	protected int port;
	protected Random rng = new Random();
	protected int selectedNumber;
	protected Message receivedMessage;
	private Serializer serializer = new Serializer();
	
	public Client (String host1, int port){
		this.host = host1;
		this.port = port;
	}
	
	public byte[] serialize(Message message) throws IOException{
        return serializer.serialize(message);
	}
	
    public Message deserialize(byte[] data) throws IOException, ClassNotFoundException {
       return serializer.deserialize(data);
    }
    
	public void run(){
		while(true){
			try {
				selectedNumber = rng.nextInt(8);
				send(new Message(strings[selectedNumber]));
				System.out.println("Westron Sent to Server: "+ strings[selectedNumber]);
				receivedMessage = deserialize(receive());
				System.out.println("Black Speech Received From Server: " + receivedMessage.getMessage());
				Thread.sleep(2000);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
    
    public abstract void send(Message message) throws IOException, InterruptedException;
    public abstract byte [] receive() throws IOException, InterruptedException;
}
