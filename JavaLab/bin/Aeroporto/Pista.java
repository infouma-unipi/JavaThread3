package Aeroporto;

public class Pista { 
	public int postoDecollo;
	public int postoAtterraggio;

	public Aereo atterrante;
	public Aereo decollante;

	// costruttore della classe Pista
	public Pista() { 
		this.postoDecollo = 1; // ha due variabili che stanno ad indicare se la pista è vuota o piena
		this.postoAtterraggio = 1;  
	}

	// metodo synchronized che fa atterrare l'aereo
	public synchronized void atterra(Aereo plane) { 


		if (plane.inVolo) { // se l'aereo vuole atterrare

			while (postoAtterraggio == 0) {
				System.out.println("L'aereo " + plane.id + " tenta di atterrare ma la pista è occupata");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			atterrante = plane; // per la stampa
			System.out.println(" ");
			System.out.println(" Pista libera per atterrare");
			this.postoAtterraggio = 0;
			notifyAll();

		}
	}

	// metodo synchronized che fa decollare l'aereo
	public synchronized void decolla(Aereo plane) { 


		if (!plane.inVolo) { // se l'aereo vuole decollare
			while (postoDecollo == 0) {
				System.out.println("L'aereo " + plane.id + " tenta di decollare ma la pista è occupata");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			decollante = plane; // per la stampa
			System.out.println(" ");
			System.out.println(" Pista libera per decollare");
			this.postoDecollo = 0;
			notifyAll();
		}
	}


	public synchronized boolean Controllo() { 

		while (postoDecollo == 1 || postoAtterraggio == 1) {
			try {
				wait();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public synchronized void Libera() {
		System.out.println(" ");
		System.out.println("Decollo dell'aereo " + decollante.id + " e atterraggio dell'aereo " + atterrante.id + " in corso!");
		// reset dei posti
		this.postoAtterraggio = 1;
		this.postoDecollo = 1;
		notifyAll();
	}

}
