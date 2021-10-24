import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CabinetImpl extends UnicastRemoteObject implements ICabinet{
	private ArrayList<AnimalImpl> patients;
	
	public CabinetImpl() throws RemoteException {
		super();
		patients = new ArrayList<AnimalImpl>();
	}
	
	@Override
	public AnimalImpl trouverAnimalParNom(String nomAnimal, String nomMaitre) throws RemoteException {
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
		 * On consid�re que cette m�thode charge des donn�es depuis une base de donn�es.
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
	}

	@Override
	public String toString() {
		return "[patients=" + patients + "]";
	}
}
