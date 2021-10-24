import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote {
	IAnimal trouverAnimalParNom(String nomAnimal, String nomMaitre) throws RemoteException;
	
	void enregistrerAnimal(String nomAnimal, String nomMaitre, String race, Espece espece) throws RemoteException;
}
