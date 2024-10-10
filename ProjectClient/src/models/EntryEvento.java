package models;

import java.time.LocalDateTime;

public class EntryEvento extends Entry{
	private String evento;

	public EntryEvento(LocalDateTime dataOra, String evento) {
		super(dataOra);
		this.evento = evento;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}
}
