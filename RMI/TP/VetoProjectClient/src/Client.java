import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) throws AlreadyBoundException {
		String host = (args.length < 1) ? null : args[0];
		
		try {
			System.setProperty("java.security.policy", "security/client.policy");
			System.setSecurityManager(new SecurityManager());
			
			Registry registry = LocateRegistry.getRegistry(host);
			
			ICabinet proxyCabinet = (ICabinet) registry.lookup("Cabinet");
			
			/*
			 * ces enregistrements restent disponible auprès du serveur
			 * tant qu'il tourne.
			 */
			proxyCabinet.enregistrerAnimal("Rex", "Moser", "Berger Allemand", new Espece("chien", 13));
			proxyCabinet.enregistrerAnimal("Happy", "Andrade", "Bichon", new Espece("chien", 13));
			proxyCabinet.enregistrerAnimal("Jerry", "Camembert", "", new Espece("souris", 1.5));
			
			/*
			 * cet enregistrement pousse à l'utilisation du mécanisme de codebase.
			 * la classe TortueFloride étant une sous-classe de Espece, 
			 * une exception ClassNotFoundException est levée par le serveur.
			 * Pour palier cette erreur, on spécifie au le moyen d'accès aux resources 
			 * dont il ne dispose pas en modifiant sa propirété codebase.
			 */
			proxyCabinet.enregistrerAnimal("Caroline", "Dunord", "hmmmm", new TortueFloride(20, 2));
			
			proxyCabinet.trouverAnimalParNoms("Rex", "John").getEspece().setNomEspece("XXXXXXXXXXXXXXXXXX"); // inutile !
			System.out.println("Espèce Rex John ? " + proxyCabinet.trouverAnimalParNoms("Rex", "John").getEspece().getNomEspece());
			System.out.println("Race Rex John ? " + proxyCabinet.trouverAnimalParNoms("Rex", "John").getRace()); // ajouté depuis le serveur
			System.out.println("Race Caroline Dunord ? " + proxyCabinet.trouverAnimalParNoms("Caroline", "Dunord").getRace());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}