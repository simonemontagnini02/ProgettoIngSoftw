package models;

import java.time.LocalDateTime;

public class EntrySegnalazione extends Entry{
	private String segnalazione;

	public EntrySegnalazione(LocalDateTime dataOra, String segnalazione) {
		super(dataOra);
		this.segnalazione = segnalazione;
	}

	public String getSegnalazione() {
		return segnalazione;
	}

	public void setSegnalazione(String segnalazione) {
		this.segnalazione = segnalazione;
	}
}
