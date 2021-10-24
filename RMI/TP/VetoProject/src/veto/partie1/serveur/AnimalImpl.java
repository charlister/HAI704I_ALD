package veto.partie1.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import veto.partie1.services.Espece;
import veto.partie1.services.IAnimal;

@SuppressWarnings("serial")
public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
	
	private String nom;
	private String nomMaitre;
	
//	private String espece;
	private Espece espece;
	
	private String race;
	
	private DossierSuiviImpl dossierSuiviAnimal;
	
	public AnimalImpl() throws RemoteException {
		super();
		nom = "ind�fini";
		nomMaitre = "ind�fini";
		
//		this.espece = espece;
		espece = new Espece();
		
		this.race = "ind�finie";
		dossierSuiviAnimal = new DossierSuiviImpl();
	}
	
	public AnimalImpl(String nom, String nomMaitre, /*String*/ Espece espece, String race, DossierSuiviImpl dossierSuiviAnimal) throws RemoteException {
		super();
		this.nom = nom;
		this.nomMaitre = nomMaitre;
		this.espece = espece;
		this.race = race;
		
		this.dossierSuiviAnimal = dossierSuiviAnimal;
	}
	
	
	@Override
	public String getNom() throws RemoteException {
		return nom;
	}
	@Override
	public String getNomMaitre() throws RemoteException {
		return nomMaitre;
	}
//	@Override
//	public String getEspece() throws RemoteException {
//		return espece;
//	}
	@Override
	public Espece getEspece() throws RemoteException {
		return espece;
	}
	@Override
	public String getRace() throws RemoteException {
		return null;
	}
	@Override
	public void printNomCompletAnimal() throws RemoteException {
		System.out.println(nom + " " + nomMaitre);
	}
	@Override
	public DossierSuiviImpl getDossierSuiviAnimal() throws RemoteException {
		return dossierSuiviAnimal;
	}

	@Override
	public String toString() {
		return "[nom=" + nom + ", nomMaitre=" + nomMaitre + ", espece=" + espece.toString() + ", race=" + race
				+ ", dossierSuiviAnimal=" + dossierSuiviAnimal.toString() + "]";
	}
	
	
}
