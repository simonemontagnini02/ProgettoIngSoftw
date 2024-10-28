package models;

import java.io.Serializable;

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
	public String toString() {
		return "Pilota: " + nome + " " + cognome + ", prezzo: " + prezzo;
	}
}
