package models;

import java.util.ArrayList;

public class Rosa {
	private int maxPiloti;
	private ArrayList<Pilota> piloti;

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
		return true;
	}
}
