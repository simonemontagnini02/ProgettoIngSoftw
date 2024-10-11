package controllers;

import java.io.ObjectInputStream;
import java.io.PrintWriter;

import javafx.stage.Stage;
import models.*;
import utilities.SocketManager;

public class CreazioneRosaController 
{
	private Partecipante p;
	private ListaPiloti listaPiloti;
	private Rosa rosa;
	private int creditiDisponibili, pilotiSelezionati;

	public CreazioneRosaController(Partecipante p) {
		super();
		this.p = p;
		this.listaPiloti=null;
        PrintWriter out=SocketManager.getInstance().getPrintWriter();
        try {
			ObjectInputStream is = new ObjectInputStream(SocketManager.getInstance().getInputStream());
			out.println("listaPiloti");
			this.listaPiloti= (ListaPiloti) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		this.rosa=p.getRosa();
		this.creditiDisponibili=this.p.getCrediti();
		this.pilotiSelezionati=rosa.getPiloti().size();
	}
	
	public void aggiungiPilota(Pilota pilota) {
		this.rosa.aggiungiPilota(pilota);
	}
	
	public void eliminaPilota(Pilota pilota) {
		this.rosa.eliminaPilota(pilota);
	}
	
	public void aggiungiPilota() {
		
	}

	public void creaRosa(Stage stage) {
    }
	
	public aggiornaCreditiDisponibili(int a) {
		this.creditiDisponibili+=a;
	}
	
	public aggiornaPilotiSelezionati(int a) {
		this.creditiDisponibili+=a;
	}
	
	public int getPilotiSelezionati() {
		return this.pilotiSelezionati;
	}
	
	public int getCreditiDisponibili() {
		return this.creditiDisponibili;
	}
}
