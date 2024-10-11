package controllers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class Server {
	private static Database DB;
    public static void main(String[] args) {
        int port = 8080; // La porta su cui il server ascolta
        DB= new Database();
        Amministratore simone=new Amministratore("simone");
        simone.setPassword("simone");
        Utente andriy=new Utente("andriy");
        andriy.setPassword("andriy");
        Utente damiano=new Utente("damiano");
        damiano.setPassword("damiano");
        List<Account> account= new ArrayList<>();
        account.add(simone);
        account.add(andriy);
        account.add(damiano);
        DB.setAccount(account);
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
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os= new ObjectOutputStream(clientSocket.getOutputStream());
            String message;
            // Leggere i messaggi dal client finché non viene inviato "exit"
            while ((message = in.readLine()) != null) {
                System.out.println("Messaggio dal client: " + message);
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                if(message.startsWith("login*")) {
                	String[] parts = message.split("\\*");

                	if (parts.length == 3 && parts[0].equals("login")) {
                	    String username = parts[1];
                	    String password = parts[2];
                	    for(Account a: DB.getAccount()) {
                	    	if(a.getUsername().equals(username)
                	    		&& a.getPassword().equals(password)) {
                	    		os.writeObject(a);
                	    	}
                	    }
                	} else {
                	    System.out.println("Formato del messaggio login non valido.");
                	}
                }

                // Risponde al client
                out.println("Server: ho ricevuto il tuo messaggio -> " + message);
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
