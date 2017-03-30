package exercicio1;

import java.util.Scanner;
import exercicio1.client.Client;
import exercicio1.client.ClientTCP;
import exercicio1.client.ClientUDP;
import exercicio1.server.Server1;
import exercicio1.server.Server1TCP;
import exercicio1.server.Server1UDP;
import exercicio1.server.Server2;
import exercicio1.server.Server2TCP;
import exercicio1.server.Server2UDP;

public class Main {

	private static String host1 = "localhost";
	private static int port1 = 1406;
	private static String host2 = "localhost";
	private static int port2 = 106;
	
	public static void main(String[] args) {
		Client client;
		Server1 server1;
		Server2 server2;
		
		System.out.println("1 to UDP, 2 to TCP");
		Scanner in = new Scanner(System.in);
		int protocolSelector = in.nextInt();
		
		if(protocolSelector == 1){
			server2 = new Server2UDP(port2);
			server1 = new Server1UDP(port1,host2,port2);
			client = new ClientUDP(host1, port1);
		}else{
			server2 = new Server2TCP(port2);
			server1 = new Server1TCP(port1,host2,port2);
			client = new ClientTCP(host1, port1);
		}
		
		new Thread(server2).start();
		new Thread(server1).start();
		new Thread(client).start();
	}
}
