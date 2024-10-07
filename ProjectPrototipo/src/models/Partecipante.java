package models;

public class Partecipante {
	private String username;
	private String nomeScuderia;
	private Lega lega;
	private boolean attivo;
	
	private Rosa rosa;
	private Formazione formazione;
	
	public Partecipante(String username, Lega lega) {
		this.username = username;
		this.lega = lega;
		this.attivo = true;
	}
	
	public void espelli() {
		this.attivo=false;
	}
	
	public void riammetti() {
		this.attivo=true;
	}
	
	public void creaRosa(Rosa rosa) {
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

	public void setNomeScuderia(String nomeScuderia) {
		this.nomeScuderia = nomeScuderia;
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

	public void setRosa(Rosa rosa) {
		this.rosa = rosa;
	}

	public Formazione getFormazione() {
		return formazione;
	}

	public void setFormazione(Formazione formazione) {
		this.formazione = formazione;
	}
}
