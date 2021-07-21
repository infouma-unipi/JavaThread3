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

			if (this.inVolo == true) {	 // controllo se l'aereo è in volo

				try { 
					sleep((int)(Math.random() * 20000)); 
					land.atterra(this); 
					System.out.println("L'aereo " + this.prendiId() + " può iniziare l'atterraggio"); //
					this.inVolo = false;
					
				} catch (InterruptedException e) {e.printStackTrace();}

			} else if (this.inVolo == false){// controllo se l'aereo è in volo

				try { 
					sleep((int)(Math.random() * 20000)); 
					land.decolla(this);
					System.out.println("L'aereo " + this.prendiId() + " può iniziare il decollo"); 
					this.inVolo = true;
					
				} catch (InterruptedException e) {e.printStackTrace();}
			} 

		}
	}
}
