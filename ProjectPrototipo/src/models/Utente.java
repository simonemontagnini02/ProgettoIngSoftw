package models;

import java.util.*;

public class Utente
{
	private String username, password;
	private Map<String, Lega> leghe;
	
	public Utente(String username) {
		super();
		if (username==null || username.equals("")) throw new IllegalArgumentException("Username nulla o vuota");
		this.username = username;
		this.password = null;
		this.leghe = new HashMap<String, Lega>();
	}
	
	public Lega creaLega(String nomeLega, String tipologia) {
		boolean isPrivata = false;
		if (tipologia.equals("privata")) {
			 isPrivata = true;
		}
		Lega lega = new Lega(nomeLega, isPrivata);
		
		Capo capo = new Capo(this.username, lega);
		lega.aggiungiPartecipante(capo, lega.getCodice());
		this.leghe.put(nomeLega, lega);
		
		return lega;
	}
	
	public Lega ricercaLega(String nomeLega) {
		return this.leghe.get(nomeLega);
	}
	
	public Optional<Partecipante> iscrizioneLegaPubblica(String nomeLega)
	{
		Optional<Lega> legaTrovata = UtenteController.cercaLega(nomeLega);
		
		if(legaTrovata.isPresent()) 
		{
			Lega lega=legaTrovata.get();
			Partecipante partecipante = new Partecipante(this.username, lega);
			boolean ris = lega.aggiungiPartecipante(partecipante, "");
			if(ris) {
				this.leghe.put(nomeLega, lega);
				return Optional.ofNullable(partecipante);
			}
			else {
				return Optional.empty();
			}
		}
		else {
			return Optional.empty();
		}
	}
	
	//////////// OPPURE
	public Partecipante iscrizioneLegaPubblica(String nomeLega)
	{
		Partecipante partecipante;
		Lega lega = UtenteController.cercaLega(nomeLega);
		
		if(lega!=null) {
			partecipante = new Partecipante(this.username, lega);
			boolean ris = lega.aggiungiPartecipante(partecipante, "");
			if(ris) {
				this.leghe.put(nomeLega, lega);
				return partecipante;
			}
			else {
				return null;
			}
		}
		return null;
	}
	////////////
	
	public Optional<Partecipante> iscrizioneLegaPrivata(String nomeLega, String codice)
	{
		Optional<Lega> legaTrovata = UtenteController.cercaLega(nomeLega);
		
		if(legaTrovata.isPresent()) 
		{
			Lega lega=legaTrovata.get();
			Partecipante partecipante = new Partecipante(this.username, lega);
			boolean ris = lega.aggiungiPartecipante(partecipante, codice);
			if(ris) {
				this.leghe.put(nomeLega, lega);
				return Optional.ofNullable(partecipante);
			}
			else {
				return Optional.empty();
			}
		}
		else {
			return Optional.empty();
		}
	}
	
	
	// GETTERS SETTERS
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		if (username==null || username.equals("")) throw new IllegalArgumentException("Username nulla o vuota");
		this.username = username;
	}

	public void setPassword(String password) {
		if (password==null || password.equals("")) throw new IllegalArgumentException("Password nulla o vuota");
		if (password.length()<8) throw new IllegalArgumentException("Password deve essere almeno di 8 caratteri");
		this.password = password;
	}
}