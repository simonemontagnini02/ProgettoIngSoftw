package models;

import java.io.Serializable;
import java.util.Objects;

public class Pilota implements Serializable{
	private String nome, cognome;
	private int prezzo;
	
	public Pilota (String nome, String cognome, int prezzo) {
		super();
		if (nome==null || nome.equals("")) throw new IllegalArgumentException("nome nulla o vuota");
		if (cognome==null || cognome.equals("")) throw new IllegalArgumentException("cognome nulla o vuota");
		this.nome = nome;
		this.cognome = cognome;
		this.prezzo = prezzo;
	}
	
	// GETTERS SETTERS
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, nome, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilota other = (Pilota) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(nome, other.nome) && prezzo == other.prezzo;
	}
	
}
