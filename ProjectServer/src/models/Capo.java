package models;

import controllers.AmministratoreController;

import java.time.LocalDateTime;
import java.util.*;

public class Capo extends Partecipante {
	private ArrayList<GP> gpCalcolati;

	public Capo(String username, Lega lega) {
		super(username, lega);
		gpCalcolati = new ArrayList<GP>();
	}
	
	//Se Capo non ha calcolato una giornata precedente, può calcolarla anche dopo?
	//E' possibile ricalcolare una giornata?
	public void calcolaGiornata() {
		Formazione f;
		Risultato ris;
		ArrayList<Pilota> pilotiFormazione;
		int punti;
		
		GP lastGP = AmministratoreController.lastGP(); //ultimo gp caricato dall'amministratore
		ArrayList<Punteggio> punteggi = AmministratoreController.getPunteggi();
		Map<String, Integer> classifica = this.lega.visualizzaClassifica();
		
		if(! this.gpCalcolati.contains(lastGP)) //controllo il gp non sia già stato calcolato
		{
			for(Partecipante part : this.lega.getPartecipanti().values())
			{
				if(part.isAttivo())
				{
					f = part.getFormazione();
					pilotiFormazione = f.getPiloti();
					punti = 0;
					
					for(Punteggio p : punteggi)
					{
						if(lastGP.equals(p.getGp()) && pilotiFormazione.contains(p.getPilota()))
						{
							punti += p.getPuntiGP();
						}
					}
					
					//aggiorno i risultati
					ris = new Risultato(part.getNomeScuderia(), lastGP.getNome(), punti);
					this.lega.aggiungiRisultato(ris);
					//aggiorno la classifica
					classifica.put(part.getNomeScuderia(), (classifica.get(part.getNomeScuderia()) + punti));
				}
			}
			
			this.lega.setClassifica(classifica);
			this.gpCalcolati.add(lastGP);
		}
	}
	
	public void espelliPartecipante(String username) {
		this.lega.espelliPartecipante(username);
	}
	
	public void riammettiPartecipante(String username) {
		this.lega.riammettiPartecipante(username);
	}
}
