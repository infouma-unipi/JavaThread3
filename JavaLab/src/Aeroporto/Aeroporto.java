package Aeroporto;
public class Aeroporto {
	public static void main(String[] args){ // classe main
			Pista land = new Pista(); // creo un oggetto Pista
			
		for (int i = 1; i < 10; i++) {  // crea 10 oggetti di classe Aereo
			if (i % 2 == 0) { // controlla se i è pari
				Aereo r = new Aereo (i, land, true); // crea Aereo in volo
			} else {
				Aereo n = new Aereo (i, land, false); // crea Aereo non in volo
			}
		}
	}
}