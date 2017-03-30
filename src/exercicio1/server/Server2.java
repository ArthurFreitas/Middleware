package exercicio1.server;

import java.io.IOException;

import exercicio1.common.Message;

public abstract class Server2 extends Server{
	
	public Server2(int portNumber) {
		super(portNumber);
	}

	private String upperCaseString;
	
	public Message doStuff(Message message) throws ClassNotFoundException, IOException, InterruptedException{
		upperCaseString = toUpperCase(message.getMessage()) + "!";
		return (new Message (upperCaseString));
	}
	
	public String toUpperCase(String string){
		return string.toUpperCase();
	}
}
