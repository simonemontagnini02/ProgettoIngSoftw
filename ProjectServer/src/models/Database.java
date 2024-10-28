package models;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance; // Unica istanza del Singleton

    private List<Lega> leghe;
    private Calendario calendario;
    private List<Account> account;
    private ListaPiloti listaPiloti;
    private List<Punteggio> punteggi;

    // Costruttore privato per impedire la creazione di istanze dall'esterno
    private Database() {
        this.leghe = new ArrayList<Lega>();
        this.calendario = new Calendario();
        this.account = new ArrayList<Account>();
        this.listaPiloti = new ListaPiloti();
        this.punteggi = new ArrayList<>();
    }

    // Metodo pubblico statico per ottenere l'istanza Singleton
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database(); // Crea l'istanza se non esiste
        }
        return instance; // Ritorna sempre la stessa istanza
    }
    
    //Metodo per aggiornare la lega
    public synchronized void aggiornaLeghe(Lega newLega) {
        for(int i=0; i<this.leghe.size(); i++)
        {
        	if(this.leghe.get(i).getNome().equals(newLega.getNome()))
        	{
        		this.leghe.set(i, newLega);
        		break;
        	}
        }
    }

    // Metodi synchronized per garantire sicurezza sui thread
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
