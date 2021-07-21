package Aeroporto;

public class AddettoControllo implements Runnable {
	private Pista Land;

	public AddettoControllo (Pista Land) {
		this.Land = Land;
	}

	public void run() {
		while (true) {
			if (Land.Controllo()) {
				Land.Libera();
			}
		}
	}
}

