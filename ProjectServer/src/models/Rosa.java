package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Rosa implements Serializable{
	private int maxPiloti;
	private ArrayList<Pilota> piloti;
	private int prezzo;

	public Rosa(int maxPiloti) {
		super();
		this.maxPiloti=maxPiloti;
		this.piloti=new ArrayList<Pilota>();
	}
	
	public boolean aggiungiPilota(Pilota pilota) {
		if((this.piloti.size()+1) > this.maxPiloti) {
			return false;
		}
		this.piloti.add(pilota);
		prezzo+=pilota.getPrezzo();
		return true;
	}
	
	public void eliminaPilota(Pilota pilota) {
		this.piloti.remove(pilota);
		prezzo-=pilota.getPrezzo();
	}
	
	public boolean containsPilota(Pilota pilota) {
		return this.piloti.contains(pilota);
	}

	public ArrayList<Pilota> getPiloti() {
		return piloti;
	}

	public void setPiloti(ArrayList<Pilota> piloti) {
		this.piloti = piloti;
	}

	public int getMaxPiloti() {
		return maxPiloti;
	}

	public void setMaxPiloti(int maxPiloti) {
		this.maxPiloti = maxPiloti;
	}

	public int getPrezzo() {
		return prezzo;
	}
	
}
