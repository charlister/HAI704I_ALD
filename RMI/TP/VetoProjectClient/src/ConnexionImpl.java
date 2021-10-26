import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ConnexionImpl extends UnicastRemoteObject implements IConnexion {
	private String identifiant;
	protected ConnexionImpl(String identifiant) throws RemoteException {
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
