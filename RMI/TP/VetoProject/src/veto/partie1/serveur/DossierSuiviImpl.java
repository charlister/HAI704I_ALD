package veto.partie1.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import veto.partie1.services.IDossierSuivi;

@SuppressWarnings("serial")
public class DossierSuiviImpl extends UnicastRemoteObject implements IDossierSuivi {
	private String dossierSuivi;
	
	public DossierSuiviImpl() throws RemoteException {
		super();
		this.dossierSuivi = "ind�fini";
	}
	
	public DossierSuiviImpl(String dossierSuivi) throws RemoteException {
		super();
		this.dossierSuivi = dossierSuivi;
	}

	@Override
	public String getDossierSuivi() {
		return dossierSuivi;
	}

	@Override
	public void setDossierSuivi(String dossierSuivi) {
		this.dossierSuivi = dossierSuivi;
	}

	@Override
	public String toString() {
		return "[dossierSuivi=" + dossierSuivi + "]";
	}
}
