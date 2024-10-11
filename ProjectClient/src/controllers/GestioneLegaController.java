package controllers;

import java.util.ArrayList;
import java.util.Map;

import javafx.stage.Stage;
import models.*;

public class GestioneLegaController {
	private static Utente utente;
	private static Lega lega;
	private static Partecipante partecipante;
	
	public GestioneLegaController(Utente utente, Lega lega) {
		this.utente=utente;
		this.lega=lega;
		Map<String, Partecipante> partecipanti=lega.getPartecipanti();
		for(String username : partecipanti.keySet()) {
			if(username.equals(utente.getUsername())) {
				this.partecipante=partecipanti.get(username);
			}
		}
	}
	
	public void getClassifica(Stage stage) {
		
	}
	
	public ArrayList<Risultato> getRisultati() {
		return this.lega.getRisultati();
	}
	
	public ArrayList<Rosa> getRose() {
		ArrayList<Rosa> rose=new ArrayList<Rosa>();
		for(Partecipante p : this.lega.getPartecipanti().values()) {
			rose.add(p.getRosa());
		}
		return rose;
	}
	
	public boolean isCapo() {
		if (partecipante instanceof Capo) {
			return true;
		}
		else {
			return false;
		}
	}

	public static Utente getUtente() {
		return utente;
	}

	public static Lega getLega() {
		return lega;
	}

	public static Partecipante getPartecipante() {
		return partecipante;
	}
}
