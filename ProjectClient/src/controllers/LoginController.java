package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import models.Account;
import models.Amministratore;
import models.Utente;
import utilities.SocketManager;

public class LoginController {

	public void login(String username, String password) {
        // Logica per il login
        PrintWriter out=SocketManager.getInstance().getPrintWriter();
        try {
			ObjectInputStream is = new ObjectInputStream(SocketManager.getInstance().getInputStream());
			out.println("login*"+username+"*"+password);
	        Account result;
	        result= (Account) is.readObject();
			if(result instanceof Utente) {
	        	
	        }
	        else if(result instanceof Amministratore) {
	        	
	        }
	        else {
	        	
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}