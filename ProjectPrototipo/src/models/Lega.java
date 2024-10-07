package models;

import java.util.*;

public class Lega {
	private String nome;
	private boolean isPrivata = false;
	private Regolamento regolamento;
	private String codice;
	
	private Map<String, Partecipante> partecipanti; //<userName, Partecipante>
	private int numPartecipanti, maxPartecipanti, maxCrediti, pilotiRosa, pilotiForm;
	
	private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private int index;
	
	public Lega(String nomeLega, boolean isPrivata) {
		super();
		if (nomeLega==null || nomeLega.equals("")) throw new IllegalArgumentException("nomeLega nulla o vuota");
		this.nome = nomeLega;
		this.isPrivata = isPrivata;
		
		this.codice = "";
		if(isPrivata)
		{
			Random random = new Random();
	        for(int i=0; i<8; i++)
	        {
	        	index = random.nextInt(chars.length());
	        	this.codice += chars.charAt(index);
	        }
		}
		
		this.partecipanti=new HashMap<String, Partecipante>();
		this.numPartecipanti = 0;
	}
	
	public boolean aggiungiPartecipante(Partecipante partecipante, String codice) {
		if(this.partecipanti.get(partecipante.getUsername())!=null) //controllo che non sia già iscritto l'utente nella lega
		{
			return false;
		}
		if( (codice.equals(this.codice)) && ((this.numPartecipanti+1) <= this.maxPartecipanti) ) {
			this.partecipanti.put(partecipante.getUsername(), partecipante);
			this.numPartecipanti++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean espelliPartecipante(String nomeScuderia) {
		Partecipante p;
		for(String username : this.partecipanti.keySet())
		{
			p = this.partecipanti.get(username);
			if(nomeScuderia.equals(p.getNomeScuderia()))
			{
				p.espelli();
				this.partecipanti.put(username, p);
				return true;
			}
		}
		return false;
	}
	
	public boolean riammettiPartecipante(String nomeScuderia) {
		Partecipante p;
		for(String username : this.partecipanti.keySet())
		{
			p = this.partecipanti.get(username);
			if(nomeScuderia.equals(p.getNomeScuderia()))
			{
				p.riammetti();
				this.partecipanti.put(username, p);
				return true;
			}
		}
		return false;
	}
	
	public Map<String, Rosa> visualizzaRose() {
		Map<String, Rosa> rose=new HashMap<String, Rosa>();
		for(Partecipante p : this.partecipanti.values())
		{
			rose.put(p.getUsername(), p.getRosa());
		}
		return rose;
	}

	public Map<String, Partecipante> getPartecipanti() {
		return partecipanti;
	}
	
	
	
	
	// GETTERS SETTERS
	public String getNome() {
		return nome;
	}

	public boolean isPrivata() {
		return isPrivata;
	}

	public Regolamento getRegolamento() {
		return regolamento;
	}

	public String getCodice() {
		return codice;
	}

	public void setNome(String nomeLega) {
		if (nomeLega==null || nomeLega.equals("")) throw new IllegalArgumentException("nomeLega nulla o vuota");
		this.nome = nomeLega;
	}

	public void setIsPrivata(boolean isPrivata) {
		this.isPrivata = isPrivata;
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
	
	public void setCodice(String codice) {
		this.codice = codice;
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

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Lega lega = (Lega) obj;

        return nome.equals(lega.getNome()) &&
               isPrivata == lega.isPrivata() &&
               regolamento.equals(lega.getRegolamento()) &&
               codice.equals(lega.getCodice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, isPrivata, regolamento, codice);
    }
}
