import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote {
	/**
	 * trouver un patient enregistré dans le cabinet 
	 * à partir de son nom et de celui de son maître.
	 * @param nomAnimal le nom de l'animal recherché.
	 * @param nomMaitre le nom du maître de l'animal recherché.
	 * @return un copie de l'animal s'il existe, null sinon.
	 * @throws RemoteException
	 */
	IAnimal trouverPatientParNoms(String nomAnimal, String nomMaitre) throws RemoteException;
	
	/**
	 * enregistrer un patient dans le cabinet
	 * @param nomAnimal nom du patient
	 * @param nomMaitre nom du maître du patient
	 * @param race race du patient
	 * @param espece espèce du patient
	 * @return true si le client a été ajouté avec succès, 
	 * false s'il est déjà enregistré.
	 * @throws RemoteException
	 */
	boolean enregistrerPatient(String nomAnimal, String nomMaitre, String race, Espece espece) throws RemoteException;

	/**
	 * désenregistrer un patient dans le cabinet
	 * @param nomAnimal nom du patient
	 * @param nomMaitre nom du maître du patient
	 * @return true si le client a été supprimé avec succès, 
	 * false s'il n'existe pas.
	 * @throws RemoteException
	 */
	boolean desenregistrerPatient(String nomAnimal, String nomMaitre) throws RemoteException;
	
	/**
	 * ajouter le client à la liste des vétérinaires connectés.
	 * @param connexion objet de connexion rattaché à un client.
	 * @throws RemoteException
	 */
	void seConnecter(IConnexionVeterinaire connexion) throws RemoteException;
	
	/**
	 * retirer le client de la liste des vétérinaires connectés.
	 * @param connexion objet de connexion rattaché à un client.
	 * @throws RemoteException
	 */
	void seDeconnecter(IConnexionVeterinaire connexion) throws RemoteException;
}
