import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		
		try {
//			System.setProperty("java.security.policy", "security/client.policy");
//			System.setSecurityManager(new SecurityManager());
			
			Registry registry = LocateRegistry.getRegistry(host);
			
			ICabinet proxyCabinet = (ICabinet) registry.lookup("Cabinet");
			
			/*
			 * ces enregistrements restent disponible auprès du serveur
			 * tant qu'il tourne.
			 */
			proxyCabinet.enregistrerAnimal("Rex", "Robert", "Husky de Sybérie", new Espece("chien", 13));
			proxyCabinet.enregistrerAnimal("Rex", "Robert", "Husky de Sybérie", new Espece("chien", 13));
			proxyCabinet.enregistrerAnimal("Happy", "Albert", "Bichon", new Espece("chien", 13));
			proxyCabinet.enregistrerAnimal("Jerry", "Camembert", "", new Espece("souris", 1.5));
			
			proxyCabinet.trouverAnimalParNom("Rex", "John").getEspece().setNomEspece("XXXXXXXXXXXXXXXXXX"); // inutile !
			System.out.println("Espèce Rex John ? " + proxyCabinet.trouverAnimalParNom("Rex", "John").getEspece().getNomEspece());
			System.out.println("Race Rex John ? " + proxyCabinet.trouverAnimalParNom("Rex", "John").getRace()); // ajouté depuis le serveur
			System.out.println("Race Rex Robert ? " + proxyCabinet.trouverAnimalParNom("Happy", "Albert").getRace());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}