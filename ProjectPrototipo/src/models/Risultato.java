package models;

public class Risultato {
	private String nomeScuderia, nomeGP;
	private int punteggioGP;
	
	public Risultato(String nomeScuderia, String nomeGP, int punteggioGP) {
		super();
		this.nomeScuderia = nomeScuderia;
		this.nomeGP = nomeGP;
		this.punteggioGP = punteggioGP;
	}

	public String getNomeScuderia() {
		return nomeScuderia;
	}

	public void setNomeScuderia(String nomeScuderia) {
		this.nomeScuderia = nomeScuderia;
	}

	public String getNomeGP() {
		return nomeGP;
	}

	public void setNomeGP(String nomeGP) {
		this.nomeGP = nomeGP;
	}

	public int getPunteggioGP() {
		return punteggioGP;
	}

	public void setPunteggioGP(int punteggioGP) {
		this.punteggioGP = punteggioGP;
	}
}
