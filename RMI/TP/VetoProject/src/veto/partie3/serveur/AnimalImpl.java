package veto.partie3.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import veto.partie3.services.Espece;
import veto.partie3.services.IAnimal;

@SuppressWarnings("serial")
public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
	
	private String nom;
	private String nomMaitre;
	private String race;
	
	private Espece espece;
	private DossierSuiviImpl dossierSuiviAnimal;
	
	public AnimalImpl() throws RemoteException {
		super();
		nom = "ind�fini";
		nomMaitre = "ind�fini";
		race = "ind�finie";
		
		espece = new Espece();
		dossierSuiviAnimal = new DossierSuiviImpl();
	}
	
	public AnimalImpl(String nom, String nomMaitre, String race, Espece espece, DossierSuiviImpl dossierSuiviAnimal) throws RemoteException {
		super();
		this.nom = nom;
		this.nomMaitre = nomMaitre;
		this.race = race;
		
		this.dossierSuiviAnimal = dossierSuiviAnimal;
		this.espece = espece;
	}
	
	
	@Override
	public String getNom() throws RemoteException {
		return nom;
	}
	@Override
	public String getNomMaitre() throws RemoteException {
		return nomMaitre;
	}
	@Override
	public Espece getEspece() throws RemoteException {
		return espece;
	}
	@Override
	public String getRace() throws RemoteException {
		return race;
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
		return "AnimalImpl [nom=" + nom + ", nomMaitre=" + nomMaitre + ", race=" + race + ", espece=" + espece.toString()
				+ ", dossierSuiviAnimal=" + dossierSuiviAnimal.toString() + "]";
	}
}
