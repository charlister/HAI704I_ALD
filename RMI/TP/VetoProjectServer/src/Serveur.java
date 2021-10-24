import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {

	public static void main(String[] args) throws AlreadyBoundException {
		try {
//			System.setProperty("java.security.policy", "security/serveur.policy");
//			System.setSecurityManager(new SecurityManager());
			
			CabinetImpl cabinet = new CabinetImpl();
			cabinet.chargerPatient("Rex", "John", "Husky de Sybérie", new Espece("chien loup", 13), new DossierSuiviImpl("prochiane date de vaccination : 15/10/21"));
			
			Registry registry = LocateRegistry.createRegistry(1099);
			
			if(registry == null) {
				System.err.println("Echec de cr�ation du registre !");
			}
			else {
				registry.bind("Cabinet", cabinet);
				System.out.println("Serveur prêt !");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}

