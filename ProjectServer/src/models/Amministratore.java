package models;

import java.util.*;


public class Amministratore extends Account
{

	public Amministratore(String username) {
		super(username);
		this.tipo="Amministratore";
	}
	
	public void aggiungiPilota(Pilota pilota, Database DB) {
		DB.getListaPiloti().aggiungiPilota(pilota);
	}

	public void eliminaPilota(Pilota pilota) {
		this.listaPiloti.eliminaPilota(pilota);
	}
	
	public void modificaPrezzo(Pilota pilota, int nuovoPrezzo) {
		ArrayList<Pilota> piloti = this.listaPiloti.getPiloti();
		piloti.remove(pilota);
		pilota.setPrezzo(nuovoPrezzo);
		piloti.add(pilota);
	}
	
	public void registraGP(GP gp) {
		this.calendario.aggiungiGP(gp);
	}
	
	public void eliminaGP(GP gp) {
		this.calendario.eliminaGP(gp.getNome());
	}
	
	public void registraPunteggi(ArrayList<Punteggio> punteggi) {
		this.punteggi.addAll(punteggi);
	}
	
	public ArrayList<Entry> getEntryLog() {
		return this.log.visualizzaLog();
	}
}