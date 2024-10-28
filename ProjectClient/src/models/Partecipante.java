package models;

import java.io.Serializable;

public class Partecipante implements Serializable{
	protected String username;
	protected String nomeScuderia;
	protected Lega lega;
	protected boolean attivo;
	protected int crediti;
	
	protected Rosa rosa;
	protected Formazione formazione;
	
	public Partecipante(String username, Lega lega) {
		this.username = username;
		this.lega = lega;
		this.attivo = true;
		this.crediti=lega.getMaxCrediti();
		this.rosa=new Rosa(lega.getPilotiRosa());
		this.formazione=new Formazione(lega.getPilotiForm());
	}
	
	public void espelli() {
		this.attivo=false;
	}
	
	public void riammetti() {
		this.attivo=true;
	}
	
	public void creaRosa(Rosa rosa) {
		this.crediti=this.lega.getMaxCrediti();
		for(Pilota pilota : rosa.getPiloti()) {
			this.crediti-=pilota.getPrezzo();
		}
		
		this.rosa = rosa;
	}

	public void schieraFormazione(Formazione formazione) {
		this.formazione = formazione;
	}
	
	
	// GETTERS SETTERS
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNomeScuderia() {
		return nomeScuderia;
	}

	public boolean setNomeScuderia(String nomeScuderia) {
		for(Partecipante p : this.lega.getPartecipanti().values()) 
		{
			if(nomeScuderia.equals(p.getNomeScuderia()))
			{
				return false;
			}
		}
		this.nomeScuderia = nomeScuderia;
		return true;
	}

	public Lega getLega() {
		return lega;
	}

	public void setLega(Lega lega) {
		this.lega = lega;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public Rosa getRosa() {
		return rosa;
	}

	public Formazione getFormazione() {
		return formazione;
	}

	public void setFormazione(Formazione formazione) {
		this.formazione = formazione;
	}
	
	public void setRosa(Rosa rosa) {
		this.rosa = rosa;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
}
