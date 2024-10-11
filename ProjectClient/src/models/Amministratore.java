package models;

import java.util.*;


public class Amministratore extends Account
{

	public Amministratore(String username) {
		super(username);
		this.tipo="Amministratore";
	}
	
	public void aggiungiPilota(Pilota pilota, Database DB) {		
		ListaPiloti list=DB.getListaPiloti();
		DB.getListaPiloti().aggiungiPilota(pilota);
		DB.setListaPiloti(list);
	}

	public void eliminaPilota(Pilota pilota, Database DB) {
		ListaPiloti list=DB.getListaPiloti();
		list.eliminaPilota(pilota);
		DB.setListaPiloti(list);
	}
	
	public void modificaPrezzo(Pilota pilota, int nuovoPrezzo, Database DB) {
		ListaPiloti list=DB.getListaPiloti();
		list.eliminaPilota(pilota);
		Pilota p=new Pilota(pilota.getNome(), pilota.getCognome(), nuovoPrezzo);
		list.aggiungiPilota(p);
		DB.setListaPiloti(list);
	}
	
	public void registraGP(GP gp, Database DB) {
		Calendario c= DB.getCalendario();
		c.aggiungiGP(gp);
		DB.setCalendario(c);
	}
	
	public void eliminaGP(GP gp, Database DB) {
		Calendario c= DB.getCalendario();
		c.eliminaGP(gp.getNome());
		DB.setCalendario(c);
	}
	
	public void registraPunteggi(List<Punteggio> punteggi, Database DB) {
		List<Punteggio> p=DB.getPunteggi();
		p.addAll(punteggi);
		DB.setPunteggi(p);
	}
}