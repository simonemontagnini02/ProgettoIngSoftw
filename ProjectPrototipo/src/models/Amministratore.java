package models;

import java.util.*;

public class Amministratore
{
	private String username, password;
	
	public Amministratore(String username) {
		super();
		if (username==null || username.equals("")) throw new IllegalArgumentException("Username nulla o vuota");
		this.username = username;
		this.password = null;
	}
}