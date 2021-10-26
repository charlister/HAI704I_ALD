import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnexion extends Remote {
	String getIdentifiant() throws RemoteException;
	
	void signalerDepassement(int seuil) throws RemoteException;
}
