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
	
	public String getNomeLega() {
		return this.lega.getNome();
	}
	
	public String getNomeScuderia() {
		return this.partecipante.getNomeScuderia();
	}
	
	public void getClassifica(Stage stage) {
		
	}
	
	public void getRisultati(Stage stage) {
		
	}
	
	public ArrayList<String> getNomiScuderie() {
		ArrayList<String> nomi=new ArrayList<String>();
		for(Partecipante p : this.lega.getPartecipanti().values()) {
			nomi.add(p.getNomeScuderia());
		}
		return nomi;
	}
	
	public ArrayList<String> getRosa(String nomeScuderia) {
		ArrayList<String> piloti=new ArrayList<String>();
		for(Partecipante p : this.lega.getPartecipanti().values()) {
			if(nomeScuderia.equals(p.getNomeScuderia())) {
				for(Pilota pilota : p.getRosa().getPiloti()) {
					piloti.add(new String(pilota.getNome()+" "+pilota.getCognome()));
				}
			}
		}
		return piloti;
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
