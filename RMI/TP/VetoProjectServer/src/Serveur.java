import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {

	public static void main(String[] args) throws AlreadyBoundException, MalformedURLException, NotBoundException {
		try {
			System.setProperty("java.security.policy", "security/serveur.policy");
			
			// NFS : Network File System
//			URL url = new File("C:\\Users\\Inese\\OneDrive\\Bureau\\FAC\\UMONTPELLIER\\M1\\main\\HAI704I - Architectures Logicielles Distribuées\\RMI\\TP\\VetoProjectClient\\bin").toURI().toURL();
//			ou
			URL url = new File("C:\\Users\\Inese\\OneDrive\\Bureau\\FAC\\UMONTPELLIER\\M1\\main\\HAI704I - Architectures Logicielles Distribuées\\RMI\\TP\\VetoProjectClient\\downloads\\news.jar").toURI().toURL();
        	
			System.out.println(url.toString());
        	System.setProperty("java.rmi.server.codebase", url.toString());
			System.setSecurityManager(new SecurityManager());
			
			CabinetImpl cabinet = new CabinetImpl();
			cabinet.chargerPatient("Rex", "John", "Husky de Sybérie", new Espece("chien loup", 13), new DossierSuiviImpl("prochiane date de vaccination : 15/10/21"));
			
			Registry registry = LocateRegistry.createRegistry(1099);
			
			if(registry == null) {
				System.err.println("Echec de création du registre !");
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

