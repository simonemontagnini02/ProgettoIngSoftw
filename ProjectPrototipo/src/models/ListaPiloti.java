package models;

import java.util.ArrayList;

public class ListaPiloti {
	private ArrayList<Pilota> piloti;

	public ListaPiloti() {
		super();
		// TODO Auto-generated constructor stub
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
