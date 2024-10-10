package models;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private List<Lega> leghe;
	private Calendario calendario;
	private List<Account> account;
	private ListaPiloti listaPiloti;
	private List<Punteggio> punteggi;
	public Database() {
		super();
		this.leghe = new ArrayList<Lega> ();
		this.calendario = new Calendario();
		this.account = new ArrayList<Account> ();
		this.listaPiloti = new ListaPiloti();
		this.punteggi = new ArrayList<>();
	}
	public List<Lega> getLeghe() {
		return leghe;
	}
	public void setLeghe(List<Lega> leghe) {
		this.leghe = leghe;
	}
	public Calendario getCalendario() {
		return calendario;
	}
	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	public ListaPiloti getListaPiloti() {
		return listaPiloti;
	}
	public void setListaPiloti(ListaPiloti listaPiloti) {
		this.listaPiloti = listaPiloti;
	}
	public List<Punteggio> getPunteggi() {
		return punteggi;
	}
	public void setPunteggi(List<Punteggio> punteggi) {
		this.punteggi = punteggi;
	}
	
}
