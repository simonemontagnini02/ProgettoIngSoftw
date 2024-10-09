package models;

import java.util.Objects;

public class Regola 
{
	private String nome, regola;
	private int valore;
	
	public Regola(String nome, int valore, String regola) {
		super();
		if (nome==null || nome.equals("")) throw new IllegalArgumentException("nome nulla o vuota");
		if (regola==null || regola.equals("")) throw new IllegalArgumentException("regola nulla o vuota");
		this.nome = nome;
		this.valore = valore;
		this.regola = regola;
	}

	public String getNome() {
		return nome;
	}

	public String getRegola() {
		return regola;
	}

	public int getValore() {
		return valore;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRegola(String regola) {
		this.regola = regola;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Regola regola = (Regola) obj;

        return nome.equals(regola.getNome()) &&
               valore == regola.getValore() &&
               regola.equals(regola.getRegola());
    }

	@Override
    public int hashCode() {
        return Objects.hash(nome, valore, regola);
    }
}
