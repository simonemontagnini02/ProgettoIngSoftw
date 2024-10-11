package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import utilities.SocketManager;

import java.sql.Timestamp;

public class Database {

    // Metodo per salvare un GP nel database
    public boolean saveGP(GP gp) {
        
        
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
