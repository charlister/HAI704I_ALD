import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossierSuivi extends Remote {
	String getDossierSuivi() throws RemoteException;
	
	void setDossierSuivi(String s) throws RemoteException;
}
