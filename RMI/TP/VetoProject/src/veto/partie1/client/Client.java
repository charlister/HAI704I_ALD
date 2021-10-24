package veto.partie1.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import veto.partie1.services.Espece;
import veto.partie1.services.IAnimal;
import veto.partie1.services.IDossierSuivi;

public class Client {

	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		
		try {
			System.setProperty("java.security.policy", "security/client.policy");
			System.setSecurityManager(new SecurityManager());
			
			Registry registry = LocateRegistry.getRegistry(host);
			
			IAnimal proxyAnimal1 = (IAnimal) registry.lookup("Animal1");
			
			String nomAnimal1 = proxyAnimal1.getNom();
			System.out.println("nomAnimal1 -> response : " + nomAnimal1);
			
			proxyAnimal1.printNomCompletAnimal();
			
			IDossierSuivi dossierSuiviAnimal1 = proxyAnimal1.getDossierSuiviAnimal();
//			dossierSuiviAnimal1.setDossierSuivi("XXX");
			/*
			 * une fois le dossier de suivi modifié (dossierSuiviAnimal1.setDossierSuivi("XXX");), 
			 * l'état est conservé jusqu'à l'arrêt du serveur.
			 */
			System.out.println("dossier de suivi de " + nomAnimal1 + " : " + dossierSuiviAnimal1.getDossierSuivi());
			
			Espece especeAnimal1 = proxyAnimal1.getEspece() ;
//			especeAnimal1.setNomEspece("XXX");
			/*
			 * s'agissant d'une copie d'espèce, 
			 * sa modification est locale (côté client) et n'interfère pas avec le serveur :
			 * les deux objets sont dissoci�s (aucune synchronisation entre les deux).
			 */
			System.out.println("espece de " + nomAnimal1 + " : " + especeAnimal1.getNomEspece() + " ; durée de vie : " + especeAnimal1.getDureeDeVieMoyenne());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}