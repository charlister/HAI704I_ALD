import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ConnexionVeterinaireImpl extends UnicastRemoteObject implements IConnexionVeterinaire {
	private String identifiant;
	protected ConnexionVeterinaireImpl(String identifiant) throws RemoteException {
		super();
		this.identifiant = identifiant;
	}

	@Override
	public void signalerDepassement(int seuil) throws RemoteException {
		System.out.println("Notre cabinet compte " + seuil + " patients !");
	}

	@Override
	public String getIdentifiant() throws RemoteException {
		return identifiant;
	}
}
