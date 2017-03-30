package exercicio1.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import exercicio1.common.Message;

public class ClientUDP extends Client{

    private DatagramSocket clientSocket;
    
    private InetAddress host;
	
	public ClientUDP(String host1, int port) {
		super(host1, port);
		try {
            this.clientSocket = new DatagramSocket();
            this.host  = InetAddress.getByName(host1);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void send(Message message) throws IOException, InterruptedException {
		byte[] msg = serialize(message);
        DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, this.host, this.port);
        clientSocket.send(sendPacket);
	}

	public byte[] receive() throws IOException, InterruptedException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        return receiveData;
	}
}
