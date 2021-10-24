package veto.partie2.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import veto.partie2.services.ICabinet;

public class Client {

	public static void main(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		
		try {
			System.setProperty("java.security.policy", "security/client.policy");
			System.setSecurityManager(new SecurityManager());
			
			Registry registry = LocateRegistry.getRegistry(host);
			
			ICabinet proxyCabinet = (ICabinet) registry.lookup("Cabinet");
			
			System.out.println("Race Rex John ? " + proxyCabinet.trouverAnimalParNom("Rex", "John").getRace());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}