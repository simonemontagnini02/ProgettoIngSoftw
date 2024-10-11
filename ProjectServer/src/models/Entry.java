package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entry implements Serializable{
	protected LocalDateTime dataOra;

	public Entry(LocalDateTime dataOra) {
		super();
		this.dataOra = dataOra;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
}
