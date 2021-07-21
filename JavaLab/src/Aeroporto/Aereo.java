package Aeroporto;


public class Aereo extends Thread {
	
	private Pista land;
	public boolean inVolo;
	public int id;
	
	//costruttore della classe Aereo
	public Aereo(int id, Pista land, boolean inVolo) { 
		this.id = id; // id identificativo dell'aereo
		this.land = land; // l'oggetto di tipo Pista da passare all'aereo
		this.inVolo = inVolo; // una variabile booleana che mi specifica se l'aereo è in volo o no
		start(); // il metodo start è all'interno del costruttore, quindi alla creazione dell'oggetto viene fatto partire subito lo start
	}
	
	// metodo per restituire il valore di id
	public int prendiId() {

		return this.id;
	}

	// metodo run
	public void run() { 
		while (true) { // creo un loop infinito
			synchronized(land) { // sincronizzo l'oggetto land di tipo Pista

				if (this.inVolo == true) {	 // controllo se l'aereo è in volo, faccio attendere con sleep e faccio una stampa

					try { sleep((int)(Math.random() * 3000)); System.out.println("L'aereo " + this.prendiId() + " vuole atterrare"); //
					} catch (InterruptedException e) {e.printStackTrace();}
					
				} else {// controllo se l'aereo è in volo, faccio attendere con sleep e faccio una stampa
					try { sleep((int)(Math.random() * 3000)); System.out.println("L'aereo " + this.prendiId() + " vuole decollare"); //
					} catch (InterruptedException e) {e.printStackTrace();}
				} 




				if (this.inVolo == true) { // l'aereo prova ad atterrare
					System.out.println("L'aereo " + this.prendiId() + " prova ad atterrare");
					land.atterra(); // metodo che oltre ad atterrare controlla che la pista sia libera
					 
				} else { // l'aereo prova a decollare
					System.out.println("L'aereo " + this.prendiId() + " prova a decollare");
					land.decolla();// metodo che oltre a decollare controlla che la pista sia libera
					
				}
			}
		}
	}
}
