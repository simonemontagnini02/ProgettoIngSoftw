package models;

import java.time.LocalDateTime;

public class GP 
{
	private String nome;
	private LocalDateTime data;
	
	public GP(String nome, LocalDateTime data) {
		super();
		this.nome = nome;
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}
