package models;

import java.util.Objects;
import java.util.Random;

public class LegaPrivata extends Lega{
	private String codice;
	private int index;
	private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public LegaPrivata(String nomeLega) {
		super(nomeLega);
		
		//Generazione codice
		this.codice = "";
		Random random = new Random();
        for(int i=0; i<8; i++)
        {
        	index = random.nextInt(chars.length());
        	this.codice += chars.charAt(index);
        }
	}
	
	public boolean aggiungiPartecipante(Partecipante partecipante, String codice) {
		if(this.partecipanti.get(partecipante.getUsername())!=null) //controllo che non sia già iscritto l'utente nella lega
		{
			return false;
		}
		if( this.verificaCodice(codice) && ((this.numPartecipanti+1) <= this.maxPartecipanti) ) {
			this.partecipanti.put(partecipante.getUsername(), partecipante);
			this.numPartecipanti++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verificaCodice(String codice) {
		return codice.equals(this.codice);
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LegaPrivata lega = (LegaPrivata) obj;

        return nome.equals(lega.getNome()) &&
               regolamento.equals(lega.getRegolamento()) &&
               codice.equals(lega.getCodice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, regolamento, codice);
    }
}
