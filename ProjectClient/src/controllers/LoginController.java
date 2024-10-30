package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

import javafx.stage.Stage;
import models.Account;
import models.Amministratore;
import models.Utente;
import ui.HomeAmministratore;
import ui.HomeUtente;
import utilities.SocketManager;

public class LoginController {

	public boolean login(String username, String password, Stage stage) {
        // Logica per il login
        PrintWriter out=SocketManager.getInstance().getPrintWriter();
        ObjectInputStream is = SocketManager.getInstance().getObjectInputStream();
        try {
        	System.out.println("login*"+username+"*"+password);
			out.println("login*"+username+"*"+password);
			out.flush();
	        Account result;
	        result= (Account) is.readObject();
	        if(result.getUsername().equals("********") && result.getPassword().equals("********")) {
	        	return false;
	        }
	        else if(result instanceof Utente) {
	        	HomeUtente home=new HomeUtente((Utente)result);
	        	home.showHomeUtente(stage);
	        	return true;
	        }
	        else if(result instanceof Amministratore) {
	        	HomeAmministratore home=new HomeAmministratore(new AmministratoreController((Amministratore)result));
	        	home.showHomeAmministratore(stage);
	        	return true;
	        }
	        else {
	        	return true;
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
}