package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Amministratore;
import models.GP;
import utilities.SocketManager;

public class AmministratoreController {
	
	private Amministratore amministratore;
	
	public AmministratoreController(Amministratore amministratore) {
		this.amministratore = amministratore;
	}
	
	public ArrayList<GP> getGPList() {
		ArrayList<GP> gpList = new ArrayList<>();
		try {
			SocketManager socketManager = SocketManager.getInstance();
            PrintWriter out = socketManager.getPrintWriter();
            BufferedReader in = socketManager.getBufferedReader();
            
            //Scrittura operazione e lettura GP
            out.println("ListaGP");
            String line;
            int numeroGP = Integer.parseInt(in.readLine());
            for(int i=0; i< numeroGP; i++) {
            	String nomeGP = in.readLine();
            	LocalDateTime dataGP = LocalDateTime.parse(in.readLine());
            	
            	GP gp = new GP(nomeGP, dataGP);
            	gpList.add(gp);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		return gpList;
	}
	
	public boolean saveGP(GP gp) {
		try {
        	SocketManager socketManager = SocketManager.getInstance();
            PrintWriter out = socketManager.getPrintWriter();
            BufferedReader in = socketManager.getBufferedReader();
            
            //Scrittura e lettura esito
            out.println("AggiuntaGP");
            out.println(gp.getNome() + ";" + gp.getData().toString());
			String response = in.readLine();
			if(response.equals("SUCCESSO"))
				return true;
			else {
				System.err.println("Errore dal server: "  + response);
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeGP(GP gp) {
		try {
			SocketManager socketManager = SocketManager.getInstance();
            PrintWriter out = socketManager.getPrintWriter();
            BufferedReader in = socketManager.getBufferedReader();
            
            out.println("RimozioneGP");
            out.println(gp.getNome() + ";" + gp.getData().toString());
            String response = in.readLine();
            if(response.equals("SUCCESSO")) {
            	return true;
            } else {
            	System.err.println("Errore dal server: "  + response);
				return false;
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
