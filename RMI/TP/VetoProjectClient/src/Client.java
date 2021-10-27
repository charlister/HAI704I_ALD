import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {
	/**
	 * cette fonction statique permet à la fois de supprimer le client de la liste des 
	 * clients connectés (à un certain cabinet vétéraire) stockée dans la classe 
	 * CabinetImpl et ensuite de supprimer l'objet distant qui réfère un client 
	 * connecté de l'environnement d'exécution RMI.
	 */
	static void deconnexion(ICabinet proxyCabinet, ConnexionVeterinaireImpl connexion) throws RemoteException {
		proxyCabinet.seDeconnecter(connexion);
		UnicastRemoteObject.unexportObject(connexion, true); 
	}
	
	public static void main(String[] args) throws AlreadyBoundException {
		String host = (args.length < 1) ? null : args[0];
		
		try {
			System.setProperty("java.security.policy", "security/client.policy");
			System.setSecurityManager(new SecurityManager());
			
			Registry registry = LocateRegistry.getRegistry(host);
			
			/*
			 * identification d'un cabinet vétérinaire précis.
			 * Tous les clients connectés à ce cabinet seront considérés comme 
			 * vétérinaires du cabinet.
			 */
			ICabinet proxyCabinet = (ICabinet) registry.lookup("Cabinet");
			
			/*
			 * la création d'un objet distant permet de maintenir le client en état de 
			 * connexion, ce qui lui permettra d'être notifier d'une hausse ou d'une 
			 * baisse de patient
			 */
			ConnexionVeterinaireImpl connexion = new ConnexionVeterinaireImpl("veto1");
			proxyCabinet.seConnecter(connexion);
			
			/*
			 * ces enregistrements restent disponible auprès du serveur
			 * tant qu'il tourne.
			 */
			proxyCabinet.enregistrerPatient("Rex", "Moser", "Berger Allemand", new Espece("chien", 13));
			proxyCabinet.enregistrerPatient("Happy", "Andrade", "Bichon", new Espece("chien", 13));
			proxyCabinet.enregistrerPatient("Jerry", "Camembert", "", new Espece("souris", 1.5));
			
			/*
			 * Cet enregistrement pousse à l'utilisation du mécanisme de codebase.
			 * la classe TortueFloride étant une sous-classe de Espece, 
			 * une exception ClassNotFoundException est levée par le serveur.
			 * Pour palier cette erreur, on spécifie au le moyen d'accès aux resources 
			 * dont il ne dispose pas en modifiant sa propirété codebase.
			 */
			proxyCabinet.enregistrerPatient("Caroline", "Dunord", "hmmmm", new TortueFloride(20, 2));
			
			
//			proxyCabinet.desenregistrerPatient("Rex", "Moser");
//			proxyCabinet.desenregistrerPatient("Happy", "Andrade");
//			proxyCabinet.desenregistrerPatient("Jerry", "Camembert");
			
			proxyCabinet.trouverPatientParNoms("Rex", "John").getEspece().setNomEspece("XXXXXXXXXXXXXXXXXX"); // inutile !
			
			System.out.println("Espèce Rex John ? " + (proxyCabinet.trouverPatientParNoms("Rex", "John") != null ? proxyCabinet.trouverPatientParNoms("Rex", "John").getEspece().getNomEspece() : "patient introuvable"));
			System.out.println("Race Rex John ? " + (proxyCabinet.trouverPatientParNoms("Rex", "John") != null ? proxyCabinet.trouverPatientParNoms("Rex", "John").getRace() : "patient introuvable")); 
			System.out.println("Race Caroline Dunord ? " + (proxyCabinet.trouverPatientParNoms("Caroline", "Dunord") != null ? proxyCabinet.trouverPatientParNoms("Caroline", "Dunord").getRace() : "patients introuvable"));
			
//			deconnexion(proxyCabinet, connexion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}