package models;

public class Account {
	protected String username, password;
	
	public Account(String username) {
		super();
		if (username==null || username.equals("")) throw new IllegalArgumentException("Username nulla o vuota");
		this.username = username;
		this.password = null;
	}
	
	//GETTERS SETTERS
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		if (username==null || username.equals("")) throw new IllegalArgumentException("Username nulla o vuota");
		this.username = username;
	}

	public void setPassword(String password) {
		if (password==null || password.equals("")) throw new IllegalArgumentException("Password nulla o vuota");
		if (password.length()<8) throw new IllegalArgumentException("Password deve essere almeno di 8 caratteri");
		this.password = password;
	}
}
