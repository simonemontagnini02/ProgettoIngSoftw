package models;

import java.util.ArrayList;

public class Formazione {
	private int maxPiloti;
	private ArrayList<Pilota> piloti;

	public Formazione(int maxPiloti) {
		super();
		this.maxPiloti=maxPiloti;
		this.piloti=new ArrayList<Pilota>();
	}
	
	public boolean aggiungiPilota(Pilota pilota) {
		if((this.piloti.size()+1) > this.maxPiloti) {
			return false;
		}
		this.piloti.add(pilota);
		return true;
	}
	
	public void eliminaPilota(Pilota pilota) {
		this.piloti.remove(pilota);
	}

	public ArrayList<Pilota> getPiloti() {
		return piloti;
	}

	public void setPiloti(ArrayList<Pilota> piloti) {
		this.piloti = piloti;
	}
}
