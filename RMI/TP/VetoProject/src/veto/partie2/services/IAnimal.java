package veto.partie2.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote{
	String getNom() throws RemoteException;
	
	String getNomMaitre() throws RemoteException;
	
	Espece getEspece() throws RemoteException;
	
	String getRace() throws RemoteException;
	
	void printNomCompletAnimal() throws RemoteException;

	IDossierSuivi getDossierSuiviAnimal() throws RemoteException;
}
