package veto.partie1.serveur;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import veto.partie1.services.Espece;

public class Serveur {

	public static void main(String[] args) throws AlreadyBoundException {
		try {
			System.setProperty("java.security.policy", "security/serveur.policy");
			System.setSecurityManager(new SecurityManager());
			
			AnimalImpl animal1 = new AnimalImpl("Happy", "ROM", new Espece("Canis Lupus", 13), "Husky de Sibérie", new DossierSuiviImpl());
			
			Registry registry = LocateRegistry.createRegistry(1099);
			
			if(registry == null) {
				System.err.println("Echec de création du registre !");
			}
			else {
				registry.bind("Animal1", animal1);
				System.out.println("Serveur prêt !");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}

