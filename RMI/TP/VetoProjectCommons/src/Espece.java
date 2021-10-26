import java.io.Serializable;

@SuppressWarnings("serial")
public class Espece implements Serializable {
	private String nomEspece;
	private double dureeDeVieMoyenne;
	
	public Espece() {
		super();
		nomEspece = "indéfini";
		dureeDeVieMoyenne = 0.;
	}
	
	public Espece(String nomEspece, double dureeDeVieMoyenne) {
		super();
		this.nomEspece = nomEspece;
		this.dureeDeVieMoyenne = dureeDeVieMoyenne;
	}

	public String getNomEspece() {
		return nomEspece;
	}

	public void setNomEspece(String nomEspece) {
		this.nomEspece = nomEspece;
	}

	public double getDureeDeVieMoyenne() {
		return dureeDeVieMoyenne;
	}

	public void setDureeDeVieMoyenne(double dureeDeVieMoyenne) {
		this.dureeDeVieMoyenne = dureeDeVieMoyenne;
	}

	@Override
	public String toString() {
		return "[nomEspece=" + nomEspece + ", dureeDeVieMoyenne=" + dureeDeVieMoyenne + "]";
	}
	
}
