package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.sql.Timestamp;

public class Database {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/tuo_database";
    private static final String USER = "root"; // Cambia con il tuo utente
    private static final String PASS = "password"; // Cambia con la tua password

    // Metodo per salvare un GP nel database
    public void saveGP(GP gp) {
        String query = "INSERT INTO GP (nome, data) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Imposta i valori per il PreparedStatement
            pstmt.setString(1, gp.getNome());
            pstmt.setObject(2, gp.getData()); 

            // Esegui l'insert
            pstmt.executeUpdate();
            System.out.println("GP salvato con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo per aggiornare il valore di lastGP
    public void updateLastGP(GP gp) {
        String query = "UPDATE LASTGP SET nome = ?, data = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

        	pstmt.setString(1, gp.getNome());
            pstmt.setObject(2, gp.getData());

            pstmt.executeUpdate();
            System.out.println("LastGP aggiornato con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static GP getLastGP() {
        String query = "SELECT nome, data FROM LASTGP";
        GP lastGP = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Esegui la query
            ResultSet rs = pstmt.executeQuery();

            // Se ci sono risultati
            if (rs.next()) {
                // Estrai i dati dal ResultSet
                String nome = rs.getString("nome");
                Timestamp timestamp = rs.getTimestamp("data");
                LocalDateTime data = timestamp.toLocalDateTime();

                // Crea un'istanza di GP con i dati recuperati
                lastGP = new GP(nome, data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastGP;
    }
}
