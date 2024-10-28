package controllers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.*;

public class Server {
	private static Database DB;
    public static void main(String[] args) {
        int port = 8080; // La porta su cui il server ascolta
        DB= Database.getInstance();
        Amministratore simone=new Amministratore("simone");
        simone.setPassword("password");
        Utente andriy=new Utente("andriy");
        andriy.setPassword("password");
        Utente damiano=new Utente("damiano");
        damiano.setPassword("password");
        List<Account> account= new ArrayList<>();
        account.add(simone);
        account.add(andriy);
        account.add(damiano);
        DB.setAccount(account);
        
        LegaPubblica lega = (LegaPubblica) damiano.creaLega("LegaF1", "pubblica");
        Capo capo = lega.getCapo();
        capo.setNomeScuderia("Scuderia-Damiano");
        Regolamento regolamento = new Regolamento();  
        Regola [] r = new Regola [7];  
        r[0] = new Regola ("MAX PARTECIPANTI", 4, "Numero massimo partecipanti");  
        r[1] = new Regola ("MAX CREDITI", 500, "Numero massimo crediti per partecipante");  
        r[2] = new Regola ("PILOTI ROSA", 5, "Numero massimo piloti rosa");  
        r[3] = new Regola ("PILOTI FORMAZIONE", 3, "Numero massimo piloti formazione");
        r[4] = new Regola ("Giro Veloce", 1, "Il pilota che ha completato il giro veloce guadagna un punto aggiuntivo");  
        r[5] = new Regola ("Miglior Pilota Sky", 3, "Il migliore pilota del GP eletto da Sky guadagna 3 punti aggiuntivi");  
        r[6] = new Regola ("Incidente", -3, "Penalità di tre punti in caso di incidente del pilota"); 
        regolamento.setRegole(r);  
        lega.setRegolamento(regolamento);
        
        List<Lega> leghe=new ArrayList<>();
        leghe.add(lega);
        DB.setLeghe(leghe);

        Optional<Partecipante> optional = andriy.iscrizioneLegaPubblica("LegaF1", DB);
        Partecipante p1 = optional.get();
        p1.setNomeScuderia("Scuderia-Andriy");
        
        ListaPiloti listaPiloti=new ListaPiloti();
        listaPiloti.aggiungiPilota(new Pilota("Charles", "Leclerc", 100));
        listaPiloti.aggiungiPilota(new Pilota("Carlos", "Sainz", 70));
        DB.setListaPiloti(listaPiloti);
        
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);

            // Loop infinito per accettare connessioni
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accetta una connessione dal client
                System.out.println("Nuovo client connesso");

                // Delegare la gestione del client a un nuovo thread ClientHandler
                ClientHandler clientHandler = new ClientHandler(clientSocket, DB);
                clientHandler.start(); // Avvia il thread (invece di usare new Thread().start())
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Classe per gestire la comunicazione con il singolo client, estende Thread
class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Database DB;
    private ObjectOutputStream os;

    public ClientHandler(Socket socket, Database DB) {
        this.clientSocket = socket;
        this.DB= DB;
    }

    @Override
    public void run() {
        try {
            // Creazione degli stream di input/output
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            os= new ObjectOutputStream(clientSocket.getOutputStream());
            String message;
            // Leggere i messaggi dal client finché non viene inviato "exit"
            while ((message = in.readLine()) != null) {
                System.out.println("Messaggio dal client: " + message);
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                if(message.contains("login*")) {
                	message=message.substring(4);
                	String[] parts = message.split("\\*");
                	if (parts.length == 3 && parts[0].equals("login")) {
                	    String username = parts[1];
                	    String password = parts[2];
                	    for(Account a: DB.getAccount()) {
                	    	if(a.getUsername().equals(username)
                	    		&& a.getPassword().equals(password)) {
                	    		os.writeObject(a);
                	    		break;
                	    	}
                	    }
                	} else {
                	    System.out.println("Formato del messaggio login non valido.");
                	}
                }
                
                if(message.equals("listaPiloti")) {
                	System.out.println("Richiesta lista piloti");
                	os.writeObject(DB.getListaPiloti());
                }
            }

            // Chiudere la connessione
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per chiudere la connessione
    private void closeConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Connessione chiusa con il client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
