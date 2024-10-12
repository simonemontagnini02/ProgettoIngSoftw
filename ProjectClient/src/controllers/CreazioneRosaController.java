package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import javafx.stage.Stage;
import models.*;
import utilities.SocketManager;

public class CreazioneRosaController 
{
	private Partecipante partecipante;
	private ListaPiloti listaPiloti;
	private Rosa rosa;
	private int creditiDisponibili, pilotiSelezionati, maxPilotiRosa;

	public CreazioneRosaController(Partecipante partecipante) {
		super();
		this.partecipante = partecipante;
		this.listaPiloti=null;
        try {
			ObjectInputStream is = new ObjectInputStream(SocketManager.getInstance().getInputStream());
			PrintWriter out=SocketManager.getInstance().getPrintWriter();
			out.println("listaPiloti");
			this.listaPiloti= (ListaPiloti) is.readObject();
			System.out.println("Lista Piloti ricevuta");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		this.rosa=partecipante.getRosa();
		this.creditiDisponibili=this.partecipante.getCrediti();
		this.pilotiSelezionati=rosa.getPiloti().size();
		this.maxPilotiRosa=this.partecipante.getLega().getPilotiRosa();
	}
	
	public void aggiungiPilota(Pilota pilota) {
		this.rosa.aggiungiPilota(pilota);
	}
	
	public void eliminaPilota(Pilota pilota) {
		this.rosa.eliminaPilota(pilota);
	}

	public void creaRosa() {
		this.partecipante.creaRosa(this.rosa);
    }
	
	public void aggiornaCreditiDisponibili(int a) {
		this.creditiDisponibili+=a;
	}
	
	public void aggiornaPilotiSelezionati(int a) {
		this.pilotiSelezionati+=a;
	}
	
	public ListaPiloti getListaPiloti() {
		return listaPiloti;
	}
	
	public Rosa getRosa() {
		return rosa;
	}

	public Partecipante getPartecipante() {
		return partecipante;
	}

	public int getPilotiSelezionati() {
		return this.pilotiSelezionati;
	}
	
	public int getMaxPilotiRosa() {
		return this.maxPilotiRosa;
	}
	
	public int getCreditiDisponibili() {
		return this.creditiDisponibili;
	}
}
