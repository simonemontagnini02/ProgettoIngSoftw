package models;

import java.io.Serializable;
import java.util.Objects;

public class LegaPubblica extends Lega {

	public LegaPubblica(String nomeLega) {
		super(nomeLega);
		// TODO Auto-generated constructor stub
	}
	
	public boolean aggiungiPartecipante(Partecipante partecipante) {
		if(this.partecipanti.get(partecipante.getUsername())!=null) //controllo che non sia già iscritto l'utente nella lega
		{
			return false;
		}
		if((this.numPartecipanti+1) <= this.maxPartecipanti ) {
			this.partecipanti.put(partecipante.getUsername(), partecipante);
			this.numPartecipanti++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Lega lega = (Lega) obj;

        return nome.equals(lega.getNome()) &&
               regolamento.equals(lega.getRegolamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, regolamento);
    }
}
