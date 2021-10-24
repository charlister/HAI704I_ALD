import java.io.Serializable;

@SuppressWarnings("serial")
public class Identification implements Serializable{
	private String identifiant, motDePasse;
	
	public Identification(String identifiant, String motDePasse) {
		this.setIdentifiant(identifiant);
		this.setMotDePasse(motDePasse);
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
