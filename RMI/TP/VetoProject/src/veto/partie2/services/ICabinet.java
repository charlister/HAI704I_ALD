package veto.partie2.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote {
	IAnimal trouverAnimalParNom(String nomAnimal, String nomMaitre) throws RemoteException;
}
