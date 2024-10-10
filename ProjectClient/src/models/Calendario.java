package models;

import java.util.Map;

public class Calendario {
	private Map<String, GP> gps; //(nomeGP-GP)

	public Calendario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void aggiungiGP(GP gp) {
		this.gps.put(gp.getNome(), gp);
	}
	
	public void eliminaGP(String nome) {
		this.gps.remove(nome);
	}

	public Map<String, GP> getGps() {
		return gps;
	}
	
	public GP getGp(String nome) {
		return this.gps.get(nome);
	}

	public void setGps(Map<String, GP> gps) {
		this.gps = gps;
	}
}
