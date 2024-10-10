package models;

import java.time.LocalDateTime;

public class Entry {
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
