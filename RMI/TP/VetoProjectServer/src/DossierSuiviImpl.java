import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class DossierSuiviImpl extends UnicastRemoteObject implements IDossierSuivi {
	private String dossierSuivi;
	
	public DossierSuiviImpl() throws RemoteException {
		super();
		this.dossierSuivi = "indéfini";
	}
	
	public DossierSuiviImpl(String dossierSuivi) throws RemoteException {
		super();
		this.dossierSuivi = dossierSuivi;
	}

	@Override
	public String getDossierSuivi() {
		return dossierSuivi;
	}

	@Override
	public void setDossierSuivi(String dossierSuivi) {
		this.dossierSuivi = dossierSuivi;
	}

	@Override
	public String toString() {
		return "[dossierSuivi=" + dossierSuivi + "]";
	}
}
