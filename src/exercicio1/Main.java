package exercicio1;

import java.util.Scanner;
import exercicio1.client.Client;
import exercicio1.client.ClientTCP;
import exercicio1.client.ClientUDP;

public class Main {

	private static String host1 = "localhost";
	private static int port1 = 1406;
	private static String host2 = "localhost";
	private static int port2 = 0106;
	
	public static void main(String[] args) {
		Client client;
		
		System.out.println("1 to UDP, 2 to TCP");
		Scanner in = new Scanner(System.in);
		int protocolSelector = in.nextInt();
		
		if(protocolSelector == 1){
			client = new ClientUDP(host1, port1);
		}else{
			client = new ClientTCP(host1, port1); 
		}
		
		client.run();
	}
}
