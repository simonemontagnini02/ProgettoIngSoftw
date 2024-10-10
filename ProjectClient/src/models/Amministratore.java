package models;

import java.util.*;

import controllers.AmministratoreController;

public class Amministratore extends Account
{
	private ListaPiloti listaPiloti;
	private Calendario calendario;
	private ArrayList<Punteggio> punteggi;
	private Log log;

	public Amministratore(String username) {
		super(username);
		this.listaPiloti = AmministratoreController.getListaPiloti();
		this.calendario = AmministratoreController.getCalendario();
		this.punteggi = AmministratoreController.getPunteggi();
		this.log = AmministratoreController.getLog();
	}
	
	public void aggiungiPilota(Pilota pilota) {
		this.listaPiloti.aggiungiPilota(pilota);
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