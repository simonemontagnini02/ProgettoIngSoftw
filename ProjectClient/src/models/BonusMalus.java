package models;

import java.io.Serializable;

public class BonusMalus implements Serializable{
	private String nome;
	private boolean verificato;
	
	public BonusMalus(String nome, boolean verificato) {
		super();
		this.nome = nome;
		this.verificato = verificato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isVerificato() {
		return verificato;
	}
	public void setVerificato(boolean verificato) {
		this.verificato = verificato;
	}
}
