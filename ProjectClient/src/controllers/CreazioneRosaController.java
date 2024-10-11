package controllers;

import java.io.ObjectInputStream;
import java.io.PrintWriter;

import javafx.stage.Stage;
import models.*;
import utilities.SocketManager;

public class CreazioneRosaController 
{
	private Partecipante p;

	public CreazioneRosaController(Partecipante p) {
		super();
		this.p = p;
	}
	
	public ListaPiloti getListaPiloti() {
		ListaPiloti result = null;
        PrintWriter out=SocketManager.getInstance().getPrintWriter();
        try {
			ObjectInputStream is = new ObjectInputStream(SocketManager.getInstance().getInputStream());
			out.println("listaPiloti");
	        result= (ListaPiloti) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }
	
	public Rosa getRosa() {
		Rosa result = null;
        PrintWriter out=SocketManager.getInstance().getPrintWriter();
        try {
			ObjectInputStream is = new ObjectInputStream(SocketManager.getInstance().getInputStream());
			out.println("rosa*"+p.getLega().getNome()+"*"+p.getNomeScuderia()); //Passo rosa*nomeLega*nomeScuderia
	        result= (Rosa) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
	}
	
	public void aggiungiPilota() {
		
	}

	public void creaRosa(Stage stage) {
    }
	
	public int getPilotiSelezionati() {
		return this.p.getRosa().getPiloti().size();
	}
	
	public int getCreditiDisponibili() {
		return this.p.getCrediti();
	}
}
