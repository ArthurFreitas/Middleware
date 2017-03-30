package exercicio1.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import exercicio1.common.Message;

public class Server1UDP extends Server1 {
	
	private DatagramSocket clientSocket;
    
    private InetAddress host;
    
    private DatagramSocket serverSocket;
    
    private InetAddress IPAddress;
    
    private int port;
    
    public Server1UDP(int portNumber, String host2, int portOfServer2) {
		super(portNumber, host2, portOfServer2);
		
		try {
			this.serverSocket = new DatagramSocket(portNumber);
			this.clientSocket = new DatagramSocket();
            this.host  = InetAddress.getByName(host2);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendToServer(Message message) throws IOException, InterruptedException {
		byte[] msg = serializer.serialize(message);
        DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, this.host, portOfServer2);
        clientSocket.send(sendPacket);
		
	}

	@Override
	public byte[] receiveFromServer() throws IOException, InterruptedException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        return receiveData;
	}

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
