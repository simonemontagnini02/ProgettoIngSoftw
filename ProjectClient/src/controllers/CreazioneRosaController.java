package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javafx.stage.Stage;
import models.*;
import utilities.SocketManager;

public class CreazioneRosaController 
{
	private Partecipante partecipante;
	private ListaPiloti listaPiloti;
	private Rosa rosa;
	private int creditiDisponibili, pilotiRimasti, maxPilotiRosa;
	private Lega lega;

	public CreazioneRosaController(Partecipante partecipante, Lega lega) {
		super();
		this.partecipante = partecipante;
		this.lega=lega;
		this.listaPiloti=null;
        try {
			ObjectInputStream is = SocketManager.getInstance().getObjectInputStream();
			PrintWriter out=SocketManager.getInstance().getPrintWriter();
			out.println("listaPiloti");
			this.listaPiloti= (ListaPiloti) is.readObject();
			System.out.println("Lista Piloti ricevuta");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		this.rosa=partecipante.getRosa();
		this.creditiDisponibili=this.partecipante.getCrediti();
		this.pilotiRimasti=rosa.getMaxPiloti() - rosa.getPiloti().size();
		this.maxPilotiRosa=this.partecipante.getLega().getPilotiRosa();
	}
	
	public void aggiungiPilota(Pilota pilota) {
		this.rosa.aggiungiPilota(pilota);
	}
	
	public void eliminaPilota(Pilota pilota) {
		this.rosa.eliminaPilota(pilota);
	}

	public void creaRosa() {
		this.pilotiRimasti=this.rosa.getMaxPiloti()-this.rosa.getPiloti().size();
		this.partecipante.creaRosa(this.rosa);
		System.out.println("Nuova Rosa:"+ this.lega.getPartecipante(this.partecipante.getUsername()).getRosa().getPiloti());
		/*
		try {
			ObjectOutputStream os = SocketManager.getInstance().getObjectOutputStream();
			PrintWriter out=SocketManager.getInstance().getPrintWriter();
			out.println("aggiornaLega");
			out.flush();
			os.writeObject(this.lega);
			os.flush();
			System.out.println("Lega aggiornata");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
    }
	
	public void aggiornaCreditiDisponibili(int a) {
		this.creditiDisponibili+=a;
	}
	
	public void aggiornaPilotiRimasti(int a) {
		this.pilotiRimasti+=a;
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

	public int getPilotiRimasti() {
		return this.pilotiRimasti;
	}
	
	public int getMaxPilotiRosa() {
		return this.maxPilotiRosa;
	}
	
	public int getCreditiDisponibili() {
		return this.creditiDisponibili;
	}
}
