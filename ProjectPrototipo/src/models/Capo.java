package models;

public class Capo extends Partecipante {

	public Capo(String username, Lega lega) {
		super(username, lega);
		// TODO Auto-generated constructor stub
	}
	
	public boolean calcolaGiornata() {
		//IMPLEMENTA METODO
		return true;
	}
	
	public boolean espelliPartecipante(String nomeScuderia) {
		return this.getLega().espelliPartecipante(nomeScuderia);
	}
	
	public boolean riammettiPartecipante(String nomeScuderia) {
		return this.getLega().riammettiPartecipante(nomeScuderia);
	}
}
