
@SuppressWarnings("serial")
public class TortueFloride extends Espece {
	double taille; // en cm
	double masse; // en kg
	
	TortueFloride() {
		super("Tortue de Floride", 30);
		taille = 0;
		masse = 0;
		
	}
	
	TortueFloride(int taille, int masse) {
		super("Tortue de Floride", 30);
		this.taille = taille;
		this.masse = masse;
	}

	public double getTaille() {
		return taille;
	}

	public void setTaille(double taille) {
		this.taille = taille;
	}

	public double getMasse() {
		return masse;
	}

	public void setMasse(double masse) {
		this.masse = masse;
	}

	@Override
	public String toString() {
		return "[taille=" + taille + "]";
	}
}
