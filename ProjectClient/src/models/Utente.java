package models;


import java.io.Serializable;
import java.util.*;

public class Utente extends Account
{
	private Map<String, Lega> leghe;
	
	public Utente(String username) {
		super(username);
		this.leghe = new HashMap<String, Lega>();
	}
	
	public Map<String, Lega> getLeghe() {
		return leghe;
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
	
	public Optional<Partecipante> iscrizioneLegaPubblica(String nomeLega, Database DB)
	{
		List<Lega> leghe=DB.getLeghe();
		for(Lega l : leghe) {
			if(l.getNome().equals(nomeLega) && l instanceof LegaPubblica) {
				LegaPubblica lp= (LegaPubblica) l;
				Partecipante p=new Partecipante(this.getUsername(),l);
				if(lp.aggiungiPartecipante(p)) {
					this.leghe.put(nomeLega, l);
					return Optional.of(p);
				}
			}
		}
		return Optional.empty();
	}
	////////////
	
	public Optional<Partecipante> iscrizioneLegaPrivata(String nomeLega, String codice, Database DB)
	{
		List<Lega> leghe=DB.getLeghe();
		for(Lega l : leghe) {
			if(l.getNome().equals(nomeLega) && l instanceof LegaPrivata) {
				LegaPrivata lp= (LegaPrivata) l;
				Partecipante p=new Partecipante(this.getUsername(),l);
				if(lp.aggiungiPartecipante(p, codice)) {
					this.leghe.put(nomeLega, l);
					return Optional.of(p);
				}
			}
		}
		return Optional.empty();
	}
}