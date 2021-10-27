import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CabinetImpl extends UnicastRemoteObject implements ICabinet{
	private static final int[] SEUILS = {9, 12, 100, 500, 1000};
	
	private ArrayList<AnimalImpl> patients;
	
	private ArrayList<IConnexionVeterinaire> veterinaires;
	
	public CabinetImpl() throws RemoteException {
		super();
		patients = new ArrayList<AnimalImpl>();
		veterinaires = new ArrayList<IConnexionVeterinaire>();
	}
	
	@Override
	public AnimalImpl trouverPatientParNoms(String nomAnimal, String nomMaitre) throws RemoteException {
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
	public boolean enregistrerPatient(String nomAnimal, String nomMaitre, String race, Espece espece) throws RemoteException {
		for(AnimalImpl x : patients) {
			if (x.getNom().equals(nomAnimal) && x.getNomMaitre().equals(nomMaitre)) {
				System.err.println("enregistrerAnimal : " + nomAnimal + " " + nomMaitre + " déjà existant !");
				return false;
			}
		}
		
		patients.add(new AnimalImpl(nomAnimal, nomMaitre, race, espece, new DossierSuiviImpl()));
		System.out.println("enregistrerAnimal : " + nomAnimal + " " + nomMaitre + " enregistré !");
		
		/*
		 * on vérifie si un seuil a été franchi à chaque ajout pour alerter 
		 * les clients.
		 */
		for(int s : SEUILS) {
			if(patients.size() == s) {
				for(IConnexionVeterinaire c : veterinaires) {
					c.signalerDepassement(s);
				}
				break;
			}
		}
		return true;
	}
	
	
	@Override
	public boolean desenregistrerPatient(String nomAnimal, String nomMaitre) throws RemoteException {
		for(int i = 0; i<patients.size();  i++) 
			if(patients.get(i).getNom().equals(nomAnimal) && patients.get(i).getNomMaitre().equals(nomMaitre)) {
				patients.remove(i);
				return true;
			}
		return false;
	}

	@Override
	public void seConnecter(IConnexionVeterinaire connexion) throws RemoteException {
		veterinaires.add(connexion);
	}

	@Override
	public void seDeconnecter(IConnexionVeterinaire connexion) throws RemoteException {
//		System.out.println("Avant déconnexion : ");
//		for(IConnexionVeterinaire c : veterinaires)
//			System.out.println(c);
		
		veterinaires.remove(connexion);
		
//		System.out.println("Après déconnexion : ");
//		for(IConnexionVeterinaire c : veterinaires)
//			System.out.println(c);
	}

	@Override
	public String toString() {
		return "[patients=" + patients + "]";
	}
}
