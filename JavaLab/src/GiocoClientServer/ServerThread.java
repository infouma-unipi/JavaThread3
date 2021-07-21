package GiocoClientServer;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {
	private Socket soc = null;				//Inizializza socket a null

	public ServerThread(Socket socket) {
		soc = socket;
	}

	public void run() {
		System.out.println("Connessione al client riuscita");	//Stampa sul monitor del server
		String clientInput;		//Client input memorizzato come String
		int tentativoClient;	//Client input memorizzato come int
		int contatore = 0;		//Contatore per memorizzare i tentativi del client, così da fissare un record da battere e rendere il gioco più interessante

		int numeroSegreto = (int) (Math.random() * 20); //Crea un numero random da 0 a 20

		try {
			Scanner in = new Scanner(soc.getInputStream());			
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

			while (true) { // loop infinito
				sleep((int)(Math.random() * 1500));
				clientInput = in.nextLine();						//Memorizza l'input del client
				tentativoClient = Integer.parseInt(clientInput);	//Converte l'input del client a int
				
				if (tentativoClient == numeroSegreto) {					//Controlla che il numero generato dal client sia uguale al numero generato dal server
					out.println("Congratulazioni hai indovinato! Il numero era: "	//Stampa le congratulazioni
							+ tentativoClient + ". Ci sei riuscito con " + contatore + " tentativi! ");
					numeroSegreto = (int) (Math.random() * 20); //Crea un nuovo numero random da 0 a 20 per continuare a giocare
					contatore = 0;
				}
				else if (tentativoClient < numeroSegreto) {					//Se il numero del client è minore del numero segreto

					out.println(tentativoClient + " A: numero troppo basso!");
					contatore ++;									//aggiunge 1 al contatore
				}
				else if (tentativoClient > numeroSegreto) {				//Se il numero del client è maggiore del numero segreto

					out.println(tentativoClient + " B: numero troppo alto!");
					contatore ++;									//aggiunge 1 al contatore
				}
				if (contatore == 50) {	// se dopo 50 tentativi ancora non ha trovato il numero esce
									
					out.println("Numero di tentativi esauriti. Alla prossima!");
					break;										//Break dal loop infinito
				}

			}
			//Chiude tutte le connessioni
			out.close();			
			in.close();
			soc.close();
		}
		catch (Exception e) {										//Se il client si è disconnesso dal server
			System.err.println("Connessione del client terminata");	
		}
	}
}
