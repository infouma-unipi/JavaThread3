package Aeroporto;

public class Pista { 
	public boolean partenza;
	public boolean arrivo;
	public static Aereo atterrante;
	public static Aereo decollante;

	// costruttore della classe Pista
	public Pista() { 
		partenza = false; // ha due variabili booleane partenza ed arrivo che stanno ad indicare se la pista è vuota o piena
		arrivo = false;   // la pista è libera quando entrambi i booleani sono true
	}

	// metodo synchronized che fa atterrare l'aereo
	public synchronized void atterra() { 

		Aereo plane = (Aereo) Thread.currentThread();	// crea un currentThread

		if (partenza == false) { // se partenza è false la cambia in true
			
			System.out.println("L'aereo " + plane.id + " aspetta che un aereo decolli.");
			
			partenza = true;
			atterrante = plane; // oggetto di classe aereo che uso per stampare
			
		} else {
			try {
				System.out.println("... ma la pista è già occupata dall'aereo " + atterrante.id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (Controllo()) { // controlla che la pista sia libera. Il metodo è bloccante. Quando si sblocca l'aereo atterra.
			System.out.println("Attenzione! L'aereo " + atterrante.id + " è atterrato, l'aereo " + decollante.id + " è partito");
			try {
				wait(1500); // serve ad evitare l'effetto ping pong
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				wait(); // e si mette in attesa
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}

	// metodo synchronized che fa decollare l'aereo
	public synchronized void decolla() { 

		Aereo plane = (Aereo) Thread.currentThread();	// crea un currentThread

		if (arrivo == false) { // se arrivo è falso lo cambia in true

			System.out.println("L'aereo " + plane.id + " aspetta che un aereo atterri.");

			arrivo = true;
			decollante = plane; // oggetto di classe aereo che uso per stampare
			
		} else {
			try {
				System.out.println("... ma la pista è già occupata dall'aereo " + decollante.id);
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (Controllo()) { // controlla che la pista sia libera. Il metodo è bloccante. Quando si sblocca l'aereo decolla.
			System.out.println("Attenzione! L'aereo " + atterrante.id + " è atterrato, l'aereo " + decollante.id + " è partito");
			try {
				wait(1500); // serve ad evitare l'effetto ping pong
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				wait(); // e si mette in attesa
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}

	// metodo Synchronized che controlla se la pista è libera
	public synchronized boolean Controllo() { 
		
		if ((partenza == true) && (arrivo == true)) {

			partenza = false; // primo comando assegna false a partenza e arrivo
			arrivo = false;
			atterrante.inVolo = false;
			decollante.inVolo = true;
			notifyAll(); // notifica tutti i Thread in attesa
	
			return true;
		} else {
			return false;
		}
	}
}
//fine synchronized
