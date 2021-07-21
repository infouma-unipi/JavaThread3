package GiocoClientServer;

import java.net.ServerSocket;

public class Server {
	public static void main(String[] args) throws Exception {
		
		ServerSocket s = new ServerSocket(8880);		//Crea un nuovo server, con porta 8880
		System.out.println("Benvenuto sul server!");
		//System.out.println(s);
		System.out.println("");
		boolean itsOk = true;								
		
		while (itsOk) {
			new ServerThread(s.accept()).start(); //Crea una nuova istanza di ServerThread con ogni nuova connessione e la fa partire
		}
		
		s.close();
		System.out.println("Fine.");
	}
}