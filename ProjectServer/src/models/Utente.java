package models;

import controllers.UtenteController;
import java.util.*;

public class Utente extends Account
{
	private Map<String, Lega> leghe;
	
	public Utente(String username) {
		super(username);
		this.tipo="Utente";
		this.leghe = new HashMap<String, Lega>();
	}
	
	public Lega creaLega(String nomeLega, String tipologia) {
		Lega lega;
		if (tipologia.equals("privata")) {
			lega = new LegaPrivata(nomeLega);
		}
		else
		{
			lega = new LegaPubblica(nomeLega);
		}
		
		Capo capo = new Capo(this.username, lega);
		lega.setCapo(capo);
		this.leghe.put(nomeLega, lega);
		
		return lega;
	}
	
	public Lega ricercaLega(String nomeLega) {
		return this.leghe.get(nomeLega);
	}
	
	public Optional<Partecipante> iscrizioneLegaPubblica(String nomeLega)
	{
		Optional<LegaPubblica> legaTrovata = UtenteController.cercaLegaPubblica(nomeLega);
		
		if(legaTrovata.isPresent()) 
		{
			LegaPubblica lega=legaTrovata.get();
			Partecipante partecipante = new Partecipante(this.username, lega);
			boolean ris = lega.aggiungiPartecipante(partecipante);
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
		LegaPubblica lega = UtenteController.cercaLegaPubblica(nomeLega);
		
		if(lega!=null) {
			partecipante = new Partecipante(this.username, lega);
			boolean ris = lega.aggiungiPartecipante(partecipante);
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
		Optional<LegaPrivata> legaTrovata = UtenteController.cercaLegaPrivata(nomeLega);
		
		if(legaTrovata.isPresent()) 
		{
			LegaPrivata lega=legaTrovata.get();
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
}