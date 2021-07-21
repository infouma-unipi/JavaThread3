package GiocoClientServer;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		try {
			Socket GameSocket = new Socket("127.0.0.1", 8880);//Indirizzo IP e numero porta uguali a quelle del server

			System.out.println("Connessione stabilita"); //Messaggio per comunicare l'avvenuta connessione
			System.out.println("");
			Scanner scannerIn = new Scanner(GameSocket.getInputStream()); //Input dal socket

			PrintWriter printerOut = new PrintWriter(GameSocket.getOutputStream(),true); //Output dal socket

			System.out.println("Benvenuta/o nel mio gioco client server! Lo scopo di questo gioco è quello di indovinare il numero che sto pensando! :)");			 

			String serverResp = "";

			while (true) { // ciclo infinito
				int numeroClient = (int) (Math.random() * 20); 		//Genera un numero random tra 0 e 20
				printerOut.println(numeroClient); 					//Manda il numero random al server
				serverResp = scannerIn.nextLine(); 				    //Risposta dal server
				System.out.println(serverResp); 			        //Stampa la risposta dal server

				if (serverResp.equals("Numero di tentativi esauriti. Alla prossima!")){// se il numero dei tentativi è finito esce dal ciclo infinito  
					break; //chiude la connessione al server
				}
			}
				System.out.println("Grazie per aver giocato!");
				//Chiude tutte le connessioni 
				printerOut.close(); 
				scannerIn.close();
				GameSocket.close();				
		} 
		catch (ConnectException e) { //Gestione dell'errore se la connessione al server non è riuscita

			System.out.println("Connessione non stabilita. Controllare che il server sia in funzione");
			System.exit(1); // termina la JVM
		} 
		catch (IOException e) { 	   //Gestione dell'errore se non ci sono input

			System.out.println("Connessione I/O non riuscita");
			System.exit(1); // termina la JVM
		}
	}
}
