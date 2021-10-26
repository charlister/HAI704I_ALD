import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CabinetImpl extends UnicastRemoteObject implements ICabinet{
	private static final int[] SEUILS = {9, 100, 500, 1000};
	
	private ArrayList<AnimalImpl> patients;
	
	private ArrayList<IConnexion> veterinaires;
	
	public CabinetImpl() throws RemoteException {
		super();
		patients = new ArrayList<AnimalImpl>();
		veterinaires = new ArrayList<IConnexion>();
	}
	
	@Override
	public AnimalImpl trouverAnimalParNoms(String nomAnimal, String nomMaitre) throws RemoteException {
		for(AnimalImpl x : patients) {
			if (x.getNom().equals(nomAnimal) && x.getNomMaitre().equals(nomMaitre)) {
				System.out.println("trouverAnimalParNom : " + nomAnimal + " " + nomMaitre + " existe !");
				return x;
			}
		}
		
		System.err.println("trouverAnimalParNom : " + nomAnimal + " " + nomMaitre + " n'existe pas !");
		return null;
	}

	public void chargerPatient(String nomAnimal, String nomMaitre, String race, 
			Espece espece, DossierSuiviImpl dossierSuivi) throws RemoteException {
		/*
		 * On considère que cette m�thode charge des donn�es depuis une base de données.
		 */
		AnimalImpl animal = new AnimalImpl(nomAnimal, nomMaitre, race, espece, dossierSuivi);
		patients.add(animal);
		System.out.println("chargerPatient : " + nomAnimal + " " + nomMaitre + " -> chargé !");
	}

	@Override
	public void enregistrerAnimal(String nomAnimal, String nomMaitre, String race, Espece espece) throws RemoteException {
		for(AnimalImpl x : patients) {
			if (x.getNom().equals(nomAnimal) && x.getNomMaitre().equals(nomMaitre)) {
				System.err.println("enregistrerAnimal : " + nomAnimal + " " + nomMaitre + " déjà existant !");
				return;
			}
		}
		patients.add(new AnimalImpl(nomAnimal, nomMaitre, race, espece, new DossierSuiviImpl()));
		System.out.println("enregistrerAnimal : " + nomAnimal + " " + nomMaitre + " enregistré !");
		for(int s : SEUILS) {
			if(patients.size() == s) {
				for(IConnexion c : veterinaires) {
					c.signalerDepassement(s);
				}
				break;
			}
		}
	}
	
	@Override
	public void enregistrerConnexion(IConnexion connexion) throws RemoteException {
		veterinaires.add(connexion);
	}

	@Override
	public String toString() {
		return "[patients=" + patients + "]";
	}
}
