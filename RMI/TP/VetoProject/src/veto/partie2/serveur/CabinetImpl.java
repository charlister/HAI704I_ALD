package veto.partie2.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import veto.partie2.services.ICabinet;
import veto.partie2.serveur.DossierSuiviImpl;
import veto.partie2.services.Espece;
import veto.partie2.serveur.AnimalImpl;

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
		
		System.out.println("trouverAnimalParNom : " + nomAnimal + " " + nomMaitre + " n'existe pas !");
		return null;
	}

	public void chargerPatient(String nomAnimal, String nomMaitre, String race, Espece espece, DossierSuiviImpl dossierSuivi) throws RemoteException {
		/*
		 * On consid�re que cette méthode charge des données depuis le BDD.
		 */
		AnimalImpl animal = new AnimalImpl(nomAnimal, nomMaitre, race, espece, dossierSuivi);
		patients.add(animal);
		System.out.println("patient " + nomAnimal + " " + nomMaitre + " charg� !");
	}

	@Override
	public String toString() {
		return "[patients=" + patients + "]";
	}
}
