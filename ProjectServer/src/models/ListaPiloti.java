package models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaPiloti implements Serializable{
	private ArrayList<Pilota> piloti;

	public ListaPiloti() {
		super();
		piloti=new ArrayList<Pilota>();
	}
	
	public void aggiungiPilota(Pilota pilota) {
		this.piloti.add(pilota);
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
