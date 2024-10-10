package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewAggiornamenti {

    public void showViewAggiornamenti(Stage stage) {

        // Carica il logo
        Image logoImage = new Image(getClass().getResourceAsStream("/img/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(150); // Imposta la larghezza del logo a 150 pixel
        logoImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto

        // Creazione dei pulsanti
        Button btnCheckUpdates = new Button("Verifica Aggiornamenti");
        Button btnUpdate = new Button("Aggiorna");
        Button btnBack = new Button("Indietro");

        double buttonWidth = 300;
        double buttonHeight = 50;
        btnCheckUpdates.setPrefWidth(buttonWidth);
        btnCheckUpdates.setPrefHeight(buttonHeight);
        btnUpdate.setPrefWidth(buttonWidth);
        btnUpdate.setPrefHeight(buttonHeight);
        btnBack.setPrefWidth(110); // Imposta una larghezza minore per il pulsante Indietro
        btnBack.setPrefHeight(30);

        // Imposta il testo in grassetto
        btnCheckUpdates.setStyle("-fx-font-weight: bold;");
        btnUpdate.setStyle("-fx-font-weight: bold;");
        btnBack.setStyle("-fx-font-weight: bold;");
        
        btnBack.setTranslateX(50);
        btnBack.setTranslateY(10);
        
        // Gestori degli eventi per i pulsanti
        btnCheckUpdates.setOnAction(event -> {
            System.out.println("Controllo degli aggiornamenti...");
        });

        btnUpdate.setOnAction(event -> {
            System.out.println("Avvio aggiornamento...");
        });

        // Gestore per il pulsante Indietro
        btnBack.setOnAction(event -> {
            // Ritorna alla schermata principale
            FantaF1App mainApp = new FantaF1App();
            try {
                mainApp.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Layout verticale (VBox) con i pulsanti centrati
        VBox vbox = new VBox(20, btnCheckUpdates, btnUpdate); // Spaziatura tra i pulsanti
        vbox.setAlignment(Pos.CENTER);

        // Layout per il pulsante "Indietro" in alto a sinistra
        VBox backButtonBox = new VBox(btnBack);
        backButtonBox.setAlignment(Pos.CENTER);
        
        // Layout per posizionare il logo in alto a sinistra
        VBox logoBox = new VBox(logoImageView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setTranslateX(-30);   
        logoBox.setPrefWidth(150); 
        logoBox.setPrefHeight(150);
        
        HBox h1 = new HBox(1100, backButtonBox, vbox, logoBox);
        h1.setAlignment(Pos.TOP_CENTER);

        // Aggiungere un'immagine di sfondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/sfondo.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Mantieni il rapporto d'aspetto
        backgroundImageView.setFitWidth(1024);
        backgroundImageView.setFitHeight(1024);
        backgroundImageView.setSmooth(true);
        
        VBox v1 = new VBox(100, h1, vbox);

        // Usa StackPane per sovrapporre l'immagine di sfondo, il logo e i pulsanti
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, v1); // Aggiungi l'immagine di sfondo, il logo, i pulsanti e il pulsante Indietro
        
        backgroundImageView.fitWidthProperty().bind(root.widthProperty());
        backgroundImageView.fitHeightProperty().bind(root.heightProperty());

        // Creazione della scena
        Scene scene = new Scene(root, 1366, 768); // Imposta la scena
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Imposta la scena al stage
        stage.setTitle("Aggiornamenti");
        stage.setScene(scene);
    }
}


