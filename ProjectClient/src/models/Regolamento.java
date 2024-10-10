package models;

import java.util.Objects;
import java.util.Optional;

public class Regolamento 
{
	private Regola[] regole;
	
	public Regolamento() {
		super();
	}

	public Optional<Regola> getRegola(String nome) {
		for(int i=0; i<regole.length; i++)
		{
			if(nome.equals(regole[i].getNome())) {
				return Optional.ofNullable(regole[i]);
			}
		}
		return Optional.empty();
	}
	
	public Regola[] getRegole() {
		return regole;
	}

	public void setRegole(Regola[] regole) {
		this.regole = regole;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Regolamento regolamento = (Regolamento) obj;

        return regole.equals(regolamento.getRegole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(regole);
    }
}
