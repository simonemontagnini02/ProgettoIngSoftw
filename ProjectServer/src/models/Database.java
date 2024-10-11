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
        this.leghe = new ArrayList<Lega>();
        this.calendario = new Calendario();
        this.account = new ArrayList<Account>();
        this.listaPiloti = new ListaPiloti();
        this.punteggi = new ArrayList<>();
    }

    public synchronized List<Lega> getLeghe() {
        return leghe;
    }

    public synchronized void setLeghe(List<Lega> leghe) {
        this.leghe = leghe;
    }

    public synchronized Calendario getCalendario() {
        return calendario;
    }

    public synchronized void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public synchronized List<Account> getAccount() {
        return account;
    }

    public synchronized void setAccount(List<Account> account) {
        this.account = account;
    }

    public synchronized ListaPiloti getListaPiloti() {
        return listaPiloti;
    }

    public synchronized void setListaPiloti(ListaPiloti listaPiloti) {
        this.listaPiloti = listaPiloti;
    }

    public synchronized List<Punteggio> getPunteggi() {
        return punteggi;
    }

    public synchronized void setPunteggi(List<Punteggio> punteggi) {
        this.punteggi = punteggi;
    }
}