package exercicio1.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server2UDP extends Server2{
    public Server2UDP(int portNumber) {
		super(portNumber);
		try {
			this.serverSocket = new DatagramSocket(portNumber);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private DatagramSocket serverSocket;
    
    private InetAddress IPAddress;
    
    private int port;
    
	@Override
	public void sendToClient(byte[] msg) throws IOException {

        if(IPAddress != null){
            DatagramPacket sendPacket =
                    new DatagramPacket(msg, msg.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
	}

	@Override
	public byte[] receiveFromClient() throws IOException {
		
        byte[] msg = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(msg,  msg.length);
        serverSocket.receive(receivePacket);
        this.IPAddress = receivePacket.getAddress();
        this.port = receivePacket.getPort();

        return msg;
	}
	

}
