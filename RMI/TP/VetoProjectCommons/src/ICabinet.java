import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote {
	/**
	 * trouver un patient enregistr� dans le cabinet 
	 * � partir de son nom et de celui de son ma�tre.
	 * @param nomAnimal le nom de l'animal recherch�.
	 * @param nomMaitre le nom du ma�tre de l'animal recherch�.
	 * @return un copie de l'animal s'il existe, null sinon.
	 * @throws RemoteException
	 */
	IAnimal trouverPatientParNoms(String nomAnimal, String nomMaitre) throws RemoteException;
	
	/**
	 * enregistrer un patient dans le cabinet
	 * @param nomAnimal nom du patient
	 * @param nomMaitre nom du ma�tre du patient
	 * @param race race du patient
	 * @param espece esp�ce du patient
	 * @return true si le client a �t� ajout� avec succ�s, 
	 * false s'il est d�j� enregistr�.
	 * @throws RemoteException
	 */
	boolean enregistrerPatient(String nomAnimal, String nomMaitre, String race, Espece espece) throws RemoteException;

	/**
	 * d�senregistrer un patient dans le cabinet
	 * @param nomAnimal nom du patient
	 * @param nomMaitre nom du ma�tre du patient
	 * @return true si le client a �t� supprim� avec succ�s, 
	 * false s'il n'existe pas.
	 * @throws RemoteException
	 */
	boolean desenregistrerPatient(String nomAnimal, String nomMaitre) throws RemoteException;
	
	/**
	 * ajouter le client � la liste des v�t�rinaires connect�s.
	 * @param connexion objet de connexion rattach� � un client.
	 * @throws RemoteException
	 */
	void seConnecter(IConnexionVeterinaire connexion) throws RemoteException;
	
	/**
	 * retirer le client de la liste des v�t�rinaires connect�s.
	 * @param connexion objet de connexion rattach� � un client.
	 * @throws RemoteException
	 */
	void seDeconnecter(IConnexionVeterinaire connexion) throws RemoteException;
}
