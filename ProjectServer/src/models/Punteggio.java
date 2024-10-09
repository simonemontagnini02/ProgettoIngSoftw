package models;

public class Punteggio {
	private int puntiGP;
	private GP gp;
	private Pilota pilota;
	
	public Punteggio(int puntiGP, GP gp, Pilota pilota) {
		super();
		this.puntiGP = puntiGP;
		this.gp = gp;
		this.pilota = pilota;
	}
	public int getPuntiGP() {
		return puntiGP;
	}
	public void setPuntiGP(int puntiGP) {
		this.puntiGP = puntiGP;
	}
	public GP getGp() {
		return gp;
	}
	public void setGp(GP gp) {
		this.gp = gp;
	}
	public Pilota getPilota() {
		return pilota;
	}
	public void setPilota(Pilota pilota) {
		this.pilota = pilota;
	}
}
