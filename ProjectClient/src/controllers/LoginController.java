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

	public void login(String username, String password, Stage stage) {
        // Logica per il login
        PrintWriter out=SocketManager.getInstance().getPrintWriter();
        ObjectInputStream is = SocketManager.getInstance().getObjectInputStream();
        try {
        	System.out.println("login*"+username+"*"+password);
			out.println("login*"+username+"*"+password);
			out.flush();
	        Account result;
	        result= (Account) is.readObject();
			if(result instanceof Utente) {
	        	HomeUtente home=new HomeUtente((Utente)result);
	        	home.showHomeUtente(stage);
	        }
	        else if(result instanceof Amministratore) {
	        	HomeAmministratore home=new HomeAmministratore(new AmministratoreController((Amministratore)result));
	        	home.showHomeAmministratore(stage);
	        }
	        else {
	        	
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}