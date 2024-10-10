package models;

import java.util.List;

public class Punteggio {
	private int puntiGP;
	private GP gp;
	private Pilota pilota;
	private List<BonusMalus> bonus;
	
	public Punteggio(int puntiGP, GP gp, Pilota pilota, List<BonusMalus> bonus) {
		super();
		this.puntiGP = puntiGP;
		this.gp = gp;
		this.pilota = pilota;
		this.bonus = bonus;
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
	public List<BonusMalus> getBonus() {
		return bonus;
	}
	public void setBonus(List<BonusMalus> bonus) {
		this.bonus = bonus;
	}
	
}
