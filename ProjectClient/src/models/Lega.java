package models;

import java.io.Serializable;
import java.util.*;

public class Lega implements Serializable{
	protected String nome;
	protected Regolamento regolamento;
	
	protected Capo capo;
	protected Map<String, Partecipante> partecipanti; //<userName, Partecipante>
	protected int numPartecipanti, maxPartecipanti, maxCrediti, pilotiRosa, pilotiForm;
	
	//protected Calendario calendario;
	//protected ListaPiloti piloti;
	protected ArrayList<Risultato> risultati;
	protected Map<String, Integer> classifica; //(nomeScuderia, punteggioTotale)
	
	public Lega(String nomeLega) {
		super();
		if (nomeLega==null || nomeLega.equals("")) throw new IllegalArgumentException("nomeLega nulla o vuota");
		this.nome = nomeLega;
		
		this.partecipanti=new HashMap<String, Partecipante>();
		this.numPartecipanti = 0;
		
		this.risultati=new ArrayList<Risultato>();
		this.classifica=new HashMap<String, Integer>();
	}
	
	public void espelliPartecipante(String username) {
		this.partecipanti.get(username).espelli();
	}
	
	public void riammettiPartecipante(String username) {
		this.partecipanti.get(username).riammetti();
	}
	
	public Map<String, Rosa> visualizzaRose() {
		Map<String, Rosa> rose=new HashMap<String, Rosa>();
		for(Partecipante p : this.partecipanti.values())
		{
			rose.put(p.getUsername(), p.getRosa());
		}
		return rose;
	}
	
	public void aggiungiRisultato(Risultato r) {
		this.risultati.add(r);
	}
	
	public ArrayList<Risultato> visualizzaRisultati() {
		return this.risultati;
	}
	
	//IMPLEMENTA
	public Map<String, Integer> visualizzaClassifica() { 
		return this.classifica;
	}

	public Map<String, Partecipante> getPartecipanti() {
		return partecipanti;
	}
	
	public Partecipante getPartecipante(String username) {
		return this.partecipanti.get(username);
	}
	
	//IMPLEMENTA aggiungiRisultato?
	
	// GETTERS SETTERS
	public String getNome() {
		return nome;
	}

	public Regolamento getRegolamento() {
		return regolamento;
	}

	public void setNome(String nomeLega) {
		if (nomeLega==null || nomeLega.equals("")) throw new IllegalArgumentException("nomeLega nulla o vuota");
		this.nome = nomeLega;
	}
	
	public void setRegolamento(Regolamento regolamento) {
		this.regolamento = regolamento;
		
		Optional<Regola> regola = regolamento.getRegola("MAX PARTECIPANTI");
		this.maxPartecipanti = regola.get().getValore();
		regola = regolamento.getRegola("MAX CREDITI");
		this.maxCrediti = regola.get().getValore();
		regola = regolamento.getRegola("PILOTI ROSA");
		this.pilotiRosa = regola.get().getValore();
		regola = regolamento.getRegola("PILOTI FORMAZIONE");
		this.pilotiForm = regola.get().getValore();
	}
	
	public Capo getCapo() {
		return capo;
	}

	public void setCapo(Capo capo) {
		this.capo = capo;
		this.partecipanti.put(capo.getUsername(), (Partecipante) capo);
	}

	public int getNumPartecipanti() {
		return numPartecipanti;
	}

	public int getMaxPartecipanti() {
		return maxPartecipanti;
	}

	public void setMaxPartecipanti(int maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}

	public int getMaxCrediti() {
		return maxCrediti;
	}

	public void setMaxCrediti(int maxCrediti) {
		this.maxCrediti = maxCrediti;
	}

	public int getPilotiRosa() {
		return pilotiRosa;
	}

	public void setPilotiRosa(int pilotiRosa) {
		this.pilotiRosa = pilotiRosa;
	}

	public int getPilotiForm() {
		return pilotiForm;
	}

	public void setPilotiForm(int pilotiForm) {
		this.pilotiForm = pilotiForm;
	}

	public void setClassifica(Map<String, Integer> classifica) {
		this.classifica = classifica;
	}

	public ArrayList<Risultato> getRisultati() {
		return risultati;
	}

	public void setRisultati(ArrayList<Risultato> risultati) {
		this.risultati = risultati;
	}

	public void setPartecipanti(Map<String, Partecipante> partecipanti) {
		this.partecipanti = partecipanti;
	}

	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
}
